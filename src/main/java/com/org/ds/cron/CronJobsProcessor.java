package com.org.ds.cron;

import com.org.ds.entity.Courier;
import com.org.ds.entity.DeliveryInfo;
import com.org.ds.enums.CourierStatus;
import com.org.ds.enums.DeliveryStatus;
import com.org.ds.service.CourierService;
import com.org.ds.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class CronJobsProcessor {

    private CourierService courierService;

    private DeliveryService deliveryService;

    @Scheduled(fixedRate = 10000)
    public void assignDelivery() {
        Optional<Courier> optionalCourier = courierService.getAvailableCouriers();
        Optional<DeliveryInfo> optionalDeliveryInfo = deliveryService.getNextDelivery();
        if (optionalDeliveryInfo.isPresent() && optionalCourier.isPresent()) {
            DeliveryInfo delivery = optionalDeliveryInfo.get();
            Courier courier = optionalCourier.get();
            delivery.setCourier(courier);
            delivery.setDeliveryStatus(DeliveryStatus.IN_PROGRESS);
            courier.setCourierStatus(CourierStatus.BUSY);

            courierService.saveCourier(courier);
            deliveryService.saveDelivery(delivery);
        }
    }

    @Scheduled(fixedRate = 300, timeUnit = TimeUnit.SECONDS)
    public void resolveDelivery() {
        Optional<DeliveryInfo> optionalDeliveryInfo = deliveryService.getInProgressDelivery();
        if (optionalDeliveryInfo.isPresent()) {
            DeliveryInfo delivery = optionalDeliveryInfo.get();
            delivery.setDeliveryStatus(DeliveryStatus.COMPLETED);
            delivery.setDeliveredAt(LocalDateTime.now());

            Courier courier = delivery.getCourier();
            courier.setCourierStatus(CourierStatus.AVAILABLE);

            courierService.saveCourier(courier);
            deliveryService.saveDelivery(delivery);
            deliveryService.resolveDelivery(delivery);
        }
    }
}
