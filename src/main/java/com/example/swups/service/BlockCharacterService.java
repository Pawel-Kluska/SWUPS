package com.example.swups.service;

import com.example.swups.entity.BlockCharacter;
import com.example.swups.repository.BlockCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockCharacterService {
    private final BlockCharacterRepository blockCharacterRepository;

    public List<BlockCharacter> getAllBlockCharacters()
    {
        return blockCharacterRepository.findAll();
    }

}
