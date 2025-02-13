package com.trustrace.survey_time.controller;


import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.ResponseCard;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    //----------------------Create----------------------

    @PostMapping
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        Response savedResponse = responseService.saveResponse(response);
        return ResponseEntity.ok(savedResponse);
    }


    //----------------------Read----------------------

    @GetMapping
    public ResponseEntity<List<Response>> getAllResponses() {
        List<Response> responses = responseService.getAllResponses();
        return responses.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(responses);
    }

    @GetMapping("/response-cards")
    public ResponseEntity<List<ResponseCard>> getAllResponseCards() {
        List<ResponseCard> responseCards = responseService.getAllResponseCards();
        return responseCards.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(responseCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable String id) {

        Response response =  responseService.getReponseById(id);
        return response == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(response);
    }

    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<ResponseCard>> getAllResponseBySurveyId(@PathVariable String surveyId) {
        List<ResponseCard> responseCardsList = responseService.getAllResponseBySurveyId(surveyId);
        return ResponseEntity.ok(responseCardsList);
    }

    //----------------------Update----------------------


    //----------------------Delete----------------------

    @DeleteMapping("/{responseId}")
    public ResponseEntity<Void> deleteResponse(@PathVariable String responseId) {
        responseService.deleteResponse(responseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<Void> deleteResponseBySurveyId(@PathVariable String surveyId) {
        responseService.deleteResponseBySurveyId(surveyId);
        return ResponseEntity.noContent().build();
    }






}
