package com.example.swups.service;

import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.repository.OpinionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionsService {
    private final OpinionRepository opinionRepository;

    public List<Opinion> getOpinions(){
        return opinionRepository.findAll();
    }
    public List<Opinion> getOpinionsByplanofstudiesid(Plansofstudy plansofstudy) {
       return opinionRepository.findOpinionByPlanofstudiesid(plansofstudy);
    }
    public Opinion getOpinionById(Integer id){
        return opinionRepository.getReferenceById(id);
    }

    public void saveOpinion(Opinion opinion) throws EmptyOpinionContentException {
        if (opinion.getContent().equals("")) {
            throw new EmptyOpinionContentException("Opinion content can't be empty");
        }
        opinionRepository.save(opinion);
    }

}
