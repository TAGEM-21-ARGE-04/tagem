package com.sau.tagem.service;

import com.sau.tagem.dto.FlowerDTO;
import com.sau.tagem.model.Flower;
import com.sau.tagem.repository.FlowerRepository;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlowerService {
    FlowerDTO save(FlowerDTO flower);

    Flower save(Flower flower);

    List<Flower> getAllByGroupId(Long id);
}
