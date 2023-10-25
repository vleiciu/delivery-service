package com.org.ds.repository;

import com.org.ds.entity.Courier;
import com.org.ds.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouriersRepository extends JpaRepository<Courier, Integer> {

    Optional<Courier> findFirstByCourierStatus(CourierStatus courierStatus);
}
