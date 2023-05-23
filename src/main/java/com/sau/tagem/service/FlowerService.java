package com.sau.tagem.service;

import com.sau.tagem.model.Flower;
import com.sau.tagem.repository.FlowerRepository;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowerService {

    private final FlowerRepository flowerRepository;

    public Flower save(Flower flower) {
        return flowerRepository.save(flower);
    }
}
