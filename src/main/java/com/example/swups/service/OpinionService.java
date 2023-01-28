package com.example.swups.service;

import com.example.swups.Utils;
import com.example.swups.entity.User;
import com.example.swups.entity.Opinion;
import com.example.swups.entity.PlanOfStudies;
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
    public List<Opinion> getOpinionsByPlanOfStudies(PlanOfStudies planOfStudies) {
       return opinionRepository.findOpinionsByPlanOfStudies(planOfStudies);
    }
    public Opinion getOpinionById(Integer id){
        return opinionRepository.getReferenceById(id);
    }
    public void saveOpinion(Opinion opinion, String planId) throws EmptyOpinionContentException, UserPrincipalNotFoundException {
        Optional<User> currentUser = Utils.getCurrentUser();

        if(currentUser.isEmpty() || currentUser.get().getAuthority().getName().equals("SENAT")) {
            throw new UserPrincipalNotFoundException("Wrong user");
        }

        PlanOfStudies planOfStudiesById = planOfStudiesService.getPlanOfStudiesById(Integer.parseInt(planId));
        opinion.setPlanOfStudies(planOfStudiesById);
        opinion.setUser(currentUser.get());
        opinion.setDateOfOpinion(Instant.now());
        opinion.setDateOfModification(Instant.now());

        if (opinion.getContent().equals("")) {
            throw new EmptyOpinionContentException("Opinion content can't be empty");
        }
        opinionRepository.save(opinion);
    }

}
