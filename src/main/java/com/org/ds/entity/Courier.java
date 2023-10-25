package com.org.ds.entity;

import com.org.ds.enums.CourierStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "COURIERS")
@Builder
@Data
public class Courier {

    @Id
    @Column(name = "COURIER_ID")
    private Integer courierId;

    @Column(name = "PAYMENT_INFO")
    private String paymentInfo;

    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus;

    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY)
    private List<DeliveryInfo> deliveryInfo;
}
