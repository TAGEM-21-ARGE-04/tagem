package com.sau.tagem.service.impl;

import com.sau.tagem.dto.FlowerDTO;
import com.sau.tagem.model.Flower;
import com.sau.tagem.repository.FlowerRepository;
import com.sau.tagem.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {
    private final ModelMapper modelMapper;
    private final FlowerRepository flowerRepository;

    @Override
    public FlowerDTO save(FlowerDTO flower) {
        return modelMapper.map(
                save(
                    modelMapper.map(
                            flower,
                            Flower.class
                    )
                ),
                FlowerDTO.class
        );
    }

    @Override
    public Flower save(Flower flower) {
        return flowerRepository.save(flower);
    }

    @Override
    public List<Flower> getAllByGroupId(Long id) {
        return flowerRepository.getAllByGroupId(id);
    }
}
