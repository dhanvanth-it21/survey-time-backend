package com.trustrace.survey_time.service;



import com.trustrace.survey_time.builder.ResponseCardBuilder;
import com.trustrace.survey_time.dao.ResponseDAO;
import com.trustrace.survey_time.dao.SurveyDAO;
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


    @Autowired
    private SurveyDAO surveyDAO;

    @Autowired
    private ResponseDAO responseDAO;

    // Save survey (POST request)
    public Response saveResponse(Response response) {
        System.out.println("Response object: " + response.getResponseObject());
        return responseDAO.save(response);
    }

    // get survey list (GET request)
    public List<Response> getAllResponses() {
        return responseDAO.findAll();
    }

    //get survey by id (GET request)
    public Response getReponseById(String id) {
        return responseDAO.findById(id);
    }

    //get all response by email id
    public List<Response> getAllResponseByEmailId(String email) {

        List<Response> responseList = responseDAO.findAll().stream().filter(
                response -> response.getEmail().equals(email)
        ).toList();
        return responseList;
    }

    //delete survey by id (DELETE request)
    public void deleteResponse(String id) {
        responseDAO.deleteById(id);
    }

    //get all response cards
    public List<ResponseCard> getAllResponseCards() {
        List<Response> responses =  responseDAO.findAll();
        Map<String, String> map = surveyIdAndTitle();
        return responses.stream()
                .map(response -> new ResponseCardBuilder()
                        .id(response.getId())
                        .surveyId(response.getSurveyId())
                        .name(response.getName())
                        .email(response.getEmail())
                        .surveyTitle(map.get(response.getSurveyId()))
                        .build())
                .toList();
    }

    //get all response card os particular survey
    public List<ResponseCard> getAllResponseBySurveyId(String surveyId) {
        List<Response> responses =  responseDAO.findAll();
        String title = getSurveyTitleById(surveyId);
        List<ResponseCard> responseCards = responses.stream()
                .filter( reponse -> reponse.getSurveyId().equals(surveyId))
                .map(response -> new ResponseCardBuilder()
        .id(response.getId())
        .surveyId(response.getSurveyId())
        .name(response.getName())
        .email(response.getEmail())
        .surveyTitle(title)
        .build())
.toList();

        return responseCards;
    }

    //get surveyTitle with surveyId
    public String getSurveyTitleById(String surveyId) {
        Survey survey = surveyDAO.findById(surveyId);
        if(survey != null) {
            return (String) survey.getSurveyObject().get("title");
        }
        return null;
    }

    //get surveyid and title of all surveys
    public Map<String, String> surveyIdAndTitle() {
        Map<String, String> map = new HashMap<>();
        List<Survey> surveys = surveyDAO.findAll();
        surveys.forEach(survey -> {
            map.put(survey.getId(), (String) survey.getSurveyObject().get("title"));
        });

        return map;
    }

    //delete all responses of the particular survey
    public void deleteResponseBySurveyId(String surveyId) {
        responseDAO.deleteBySurveyId(surveyId);
    }

}
