package com.example.swups.service;

import com.example.swups.config.Utils;
import com.example.swups.entity.Appuser;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.Plansofstudy;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.repository.OpinionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionsService {
    private final OpinionRepository opinionRepository;
    private final PlansOfStudiesService plansOfStudiesService;

    public List<Opinion> getOpinions(){
        return opinionRepository.findAll();
    }
    public List<Opinion> getOpinionsByplanofstudiesid(Plansofstudy plansofstudy) {
       return opinionRepository.findOpinionByPlanofstudiesid(plansofstudy);
    }
    public Opinion getOpinionById(Integer id){
        return opinionRepository.getReferenceById(id);
    }

    public void saveOpinion(Opinion opinion, String planId) throws EmptyOpinionContentException, UserPrincipalNotFoundException {
        Optional<Appuser> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty()) {
            throw new UserPrincipalNotFoundException("User not logged in");
        }

        Plansofstudy planOfStudiesById = plansOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        opinion.setPlanofstudiesid(planOfStudiesById);
        opinion.setUserid(currentUser.get());
        opinion.setDateofopinion(Instant.now());
        opinion.setDateofmodification(Instant.now());

        if (opinion.getContent().equals("")) {
            throw new EmptyOpinionContentException("Opinion content can't be empty");
        }
        opinionRepository.save(opinion);
    }

}
