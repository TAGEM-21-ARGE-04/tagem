package com.sau.tagem.service.impl;

import com.sau.tagem.model.Flower;
import com.sau.tagem.repository.FlowerRepository;
import com.sau.tagem.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;

    @Override
    public Flower save(Flower flower) {
        return flowerRepository.save(flower);
    }
}
