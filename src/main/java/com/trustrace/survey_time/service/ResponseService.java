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
        return null;
    }

    //delete survey by id (DELETE request)
    public void deleteResponse(String id) {
        responseRepository.deleteById(id);
    }
}
