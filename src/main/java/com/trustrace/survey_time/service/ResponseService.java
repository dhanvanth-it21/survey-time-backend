package com.trustrace.survey_time.service;



import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.ResponseCard;
import com.trustrace.survey_time.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    // Autowire the SurveyRepository
    @Autowired
    private ResponseRepository responseRepository;

    // Save survey (POST request)
    public Response saveResponse(Response response) {
        System.out.println("Response object: " + response.getResponseObject());
        return responseRepository.save(response);
    }

    // get survey list (GET request)
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    //get survey by id (GET request)
    public Response getReponseById(String id) {
        return responseRepository.findById(id).orElse(null);
    }

    //get all response by email id
    public List<Response> getAllResponseByEmailId(String email) {

        List<Response> responseList = responseRepository.findAll().stream().filter(
                response -> response.getEmail().equals(email)
        ).toList();
        return responseList;
    }

    //delete survey by id (DELETE request)
    public void deleteResponse(String id) {
        responseRepository.deleteById(id);
    }

    public List<ResponseCard> getAllResponseCards() {
        List<Response> responses =  responseRepository.findAll();

        List<ResponseCard> responseCards = responses.stream().map(
                response -> {
                    ResponseCard responseCard = new ResponseCard();
                    responseCard.setId(response.getId());
                    responseCard.setSurveyId(response.getSurveyId());
                    responseCard.setName(response.getName());
                    responseCard.setEmail(response.getEmail());
                    return  responseCard;
                }
        ).toList();

        return responseCards;
    }

    public List<ResponseCard> getAllResponseBySurveyId(String surveyId) {
        List<Response> responses =  responseRepository.findAll();

        List<ResponseCard> responseCards = responses.stream()
                .filter( reponse -> reponse.getSurveyId().equals(surveyId))
                .map(response -> {
                    ResponseCard responseCard = new ResponseCard();
                    responseCard.setId(response.getId());
                    responseCard.setSurveyId(response.getSurveyId());
                    responseCard.setName(response.getName());
                    responseCard.setEmail(response.getEmail());
                    return  responseCard;
                })
                .toList();

        return responseCards;
    }
}
