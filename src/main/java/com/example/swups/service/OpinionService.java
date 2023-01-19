package com.example.swups.service;

import com.example.swups.Utils;
import com.example.swups.entity.Appuser;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.PlanOfStudy;
import com.example.swups.exceptions.EmptyOpinionContentException;
import com.example.swups.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final PlanOfStudiesService planOfStudiesService;

    public List<Opinion> getOpinions(){
        return opinionRepository.findAll();
    }
    public List<Opinion> getOpinionsByplanofstudiesid(PlanOfStudy planOfStudy) {
       return opinionRepository.findOpinionByPlanOfStudy(planOfStudy);
    }
    public Opinion getOpinionById(Integer id){
        return opinionRepository.getReferenceById(id);
    }

    public void saveOpinion(Opinion opinion, String planId) throws EmptyOpinionContentException, UserPrincipalNotFoundException {
        Optional<Appuser> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty()) {
            throw new UserPrincipalNotFoundException("User not logged in");
        }

        PlanOfStudy planOfStudiesById = planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        opinion.setPlanOfStudy(planOfStudiesById);
        opinion.setAppuser(currentUser.get());
        opinion.setDateOfOpinion(Instant.now());
        opinion.setDateOfModification(Instant.now());

        if (opinion.getContent().equals("")) {
            throw new EmptyOpinionContentException("Opinion content can't be empty");
        }
        opinionRepository.save(opinion);
    }

}
