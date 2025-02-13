package com.trustrace.survey_time.service;

import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.template.ResponseTemplate;
import com.trustrace.survey_time.template.SurveyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    // Autowire the SurveyRepository
    @Autowired
    private SurveyTemplate surveyTemplate;

    //Autowire the ResponseService
    @Autowired
    private ResponseService responseService;

    @Autowired
    private ResponseTemplate responseTemplate;

    // Save survey (POST request)
    public Survey saveSurvey(Survey survey) {
        System.out.println("Survey object: " + survey.getSurveyObject());
        return surveyTemplate.save(survey);
    }

    // get survey list (GET request)
    public List<Survey> getAllSurveys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return surveyTemplate.findAll(pageable);
    }

    //get survey by id (GET request)
    public Survey getSurveyById(String id) {
        return surveyTemplate.findById(id);
    }

    //delete survey by id (DELETE request)
    public void deleteSurvey(String id) {
        surveyTemplate.deleteById(id);
    }

    //get survey cards (GET request)
    public List<SurveyCard> getAllSurveyCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Survey> surveys =  surveyTemplate.findAll(pageable);
        List<SurveyCard> surveyCards = surveys.stream().map(survey -> {
            SurveyCard surveyCard = new SurveyCard();
            surveyCard.setId(survey.getId());
            surveyCard.setTitle((String) survey.getSurveyObject().get("title"));
            surveyCard.setDescription((String) survey.getSurveyObject().get("description"));
            surveyCard.setActive((Boolean) survey.getSurveyObject().get("active"));
            return surveyCard;
        }).toList();
        return surveyCards;
    }

    //get all pending survey cards for the user of particular email id
    public List<SurveyCard> getPendingSurveyCards(String emailId) {
        List<Survey> surveys = surveyTemplate.getAllActiveSurveys();
        List<Response> responses = responseTemplate.getAllResponsesByEmailId(emailId);
        List<String> surveyIds = responses.stream().map(Response::getSurveyId).toList();
        List<SurveyCard> pendingSurveys = surveys.stream().filter(survey -> !surveyIds.contains(survey.getId()))
                .map(survey -> {
                    SurveyCard surveyCard = new SurveyCard();
                    surveyCard.setId(survey.getId());
                    surveyCard.setTitle((String) survey.getSurveyObject().get("title"));
                    surveyCard.setDescription((String) survey.getSurveyObject().get("description"));
                    surveyCard.setActive(true);
                    return surveyCard;
                })
                .toList();
        return pendingSurveys;
    }


    public void updateActiveStatus(String surveyId) {
        Survey survey = surveyTemplate.findById(surveyId);
        if(survey != null) {
            Boolean isActive = (Boolean) survey.getSurveyObject().get("active");
            survey.getSurveyObject().put("active", !isActive);
            surveyTemplate.save(survey);

        }
    }

}
