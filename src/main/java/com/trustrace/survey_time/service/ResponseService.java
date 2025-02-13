package com.trustrace.survey_time.service;



import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.ResponseCard;
import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.template.ResponseTemplate;
import com.trustrace.survey_time.template.SurveyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResponseService {

    // Autowire the SurveyRepository
    @Autowired
    private ResponseTemplate responseTemplate;

    @Autowired
    private SurveyTemplate surveyRepository;

    // Save survey (POST request)
    public Response saveResponse(Response response) {
        System.out.println("Response object: " + response.getResponseObject());
        return responseTemplate.save(response);
    }

    // get survey list (GET request)
    public List<Response> getAllResponses() {
        return responseTemplate.findAll();
    }

    //get survey by id (GET request)
    public Response getReponseById(String id) {
        return responseTemplate.findById(id);
    }

    //get all response by email id
    public List<Response> getAllResponseByEmailId(String email) {

        List<Response> responseList = responseTemplate.findAll().stream().filter(
                response -> response.getEmail().equals(email)
        ).toList();
        return responseList;
    }

    //delete survey by id (DELETE request)
    public void deleteResponse(String id) {
        responseTemplate.deleteById(id);
    }

    //get all response cards
    public List<ResponseCard> getAllResponseCards() {
        List<Response> responses =  responseTemplate.findAll();
        Map<String, String> map = surveyIdAndTitle();
        List<ResponseCard> responseCards = responses.stream().map(
                response -> {
                    ResponseCard responseCard = new ResponseCard();
                    responseCard.setId(response.getId());
                    responseCard.setSurveyId(response.getSurveyId());
                    responseCard.setName(response.getName());
                    responseCard.setEmail(response.getEmail());
                    responseCard.setSurveyTitle(map.get(response.getSurveyId()));
                    return  responseCard;
                }
        ).toList();

        return responseCards;
    }

    //get all response card os particular survey
    public List<ResponseCard> getAllResponseBySurveyId(String surveyId) {
        List<Response> responses =  responseTemplate.findAll();
        String title = getSurveyTitleById(surveyId);
        List<ResponseCard> responseCards = responses.stream()
                .filter( reponse -> reponse.getSurveyId().equals(surveyId))
                .map(response -> {
                    ResponseCard responseCard = new ResponseCard();
                    responseCard.setId(response.getId());
                    responseCard.setSurveyId(response.getSurveyId());
                    responseCard.setName(response.getName());
                    responseCard.setEmail(response.getEmail());
                    responseCard.setSurveyTitle(title);
                    return  responseCard;
                })
                .toList();

        return responseCards;
    }

    //get surveyTitle with surveyId
    public String getSurveyTitleById(String surveyId) {
        Survey survey = surveyRepository.findById(surveyId);
        if(survey != null) {
            return (String) survey.getSurveyObject().get("title");
        }
        return null;
    }

    //get surveyid and title of all surveys
    public Map<String, String> surveyIdAndTitle() {
        Map<String, String> map = new HashMap<>();
        List<Survey> surveys = surveyRepository.findAll();
        surveys.forEach(survey -> {
            map.put(survey.getId(), (String) survey.getSurveyObject().get("title"));
        });

        return map;
    }

    //delete all responses of the particular survey
    public void deleteResponseBySurveyId(String surveyId) {
        responseTemplate.deleteBySurveyId(surveyId);
    }

}
