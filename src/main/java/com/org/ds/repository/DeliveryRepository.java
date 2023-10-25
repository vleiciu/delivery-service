package com.org.ds.repository;

import com.org.ds.entity.DeliveryInfo;
import com.org.ds.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryInfo, Integer> {

    Optional<DeliveryInfo> findFirstByDeliveryStatus(DeliveryStatus deliveryStatus);
}
