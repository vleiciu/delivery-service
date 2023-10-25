package com.org.ds.service;

import com.org.ds.entity.Courier;
import com.org.ds.enums.CourierStatus;
import com.org.ds.repository.CouriersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourierService {

    private CouriersRepository repository;

    public Optional<Courier> getAvailableCouriers() {
        return repository.findFirstByCourierStatus(CourierStatus.AVAILABLE);
    }

    public void saveCourier(Courier courier) {
        repository.save(courier);
    }
}
