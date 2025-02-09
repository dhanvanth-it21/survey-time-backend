package com.trustrace.survey_time.service;

import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    // Autowire the SurveyRepository
    @Autowired
    private SurveyRepository surveyRepository;

    // Save survey (POST request)
    public Survey saveSurvey(Survey survey) {
        System.out.println("Survey object: " + survey.getSurveyObject());
        return surveyRepository.save(survey);
    }

    // get survey list (GET request)
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    //get survey by id (GET request)
    public Survey getSurveyById(String id) {
        return surveyRepository.findById(id).orElse(null);
    }

    //delete survey by id (DELETE request)
    public void deleteSurvey(String id) {
        surveyRepository.deleteById(id);
    }

    //get survey cards (GET request)
    public List<SurveyCard> getAllSurveyCards() {
        List<Survey> surveys =  surveyRepository.findAll();
        List<SurveyCard> surveyCards = surveys.stream().map(survey -> {
            SurveyCard surveyCard = new SurveyCard();
            surveyCard.setId(survey.getId());
            surveyCard.setTitle((String) survey.getSurveyObject().get("title"));
            surveyCard.setDescription((String) survey.getSurveyObject().get("description"));
            return surveyCard;
        }).toList();
        return surveyCards;
    }
}
