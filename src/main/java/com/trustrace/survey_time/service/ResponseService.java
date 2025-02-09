package com.trustrace.survey_time.service;



import com.trustrace.survey_time.model.Response;
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
    public Response saveSurvey(Response response) {
        System.out.println("Survey object: " + response.getResponseObject());
        return responseRepository.save(response);
    }

    // get survey list (GET request)
    public List<Response> getAllSurveys() {
        return responseRepository.findAll();
    }

    //get survey by id (GET request)
    public Response getSurveyById(String id) {
        return responseRepository.findById(id).orElse(null);
    }

    //delete survey by id (DELETE request)
    public void deleteSurvey(String id) {
        responseRepository.deleteById(id);
    }
}
