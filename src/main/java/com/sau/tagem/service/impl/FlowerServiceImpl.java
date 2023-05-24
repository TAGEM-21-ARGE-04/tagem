package com.sau.tagem.service.impl;

import com.sau.tagem.dto.FlowerDTO;
import com.sau.tagem.model.Flower;
import com.sau.tagem.repository.FlowerRepository;
import com.sau.tagem.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {
    private final ModelMapper modelMapper;
    private final FlowerRepository flowerRepository;

    @Override
    public FlowerDTO save(FlowerDTO flower) {
        return modelMapper.map(
                flowerRepository.save(
                        modelMapper.map(
                                flower,
                                Flower.class
                        )
                ),
                FlowerDTO.class
        );
    }
}
