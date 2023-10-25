package com.org.ds.entity;

import com.org.ds.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "DELIVERY_INFO")
@Builder
@Data
public class DeliveryInfo {

    @Id
    @Column(name = "DELIVERY_ID")
    private Integer deliveryId;

    @Column(name = "RESTAURANT_ID")
    private Integer restaurantId;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "CORRELATION_ID")
    private String correlationId;

    @Column(name = "SUBMITTED_AT")
    private LocalDateTime submittedAt;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DELIVERY_TIME")
    private LocalDateTime deliveryTime;

    @Column(name = "DELIVERED_AT")
    private LocalDateTime deliveredAt;

    @Column(name = "COST")
    private Double cost;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryInfo")
    private Courier courier;
}
