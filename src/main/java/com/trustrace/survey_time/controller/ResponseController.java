package com.trustrace.survey_time.controller;


import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.ResponseCard;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping()
    public Response createResponse(@RequestBody Response response) {
        return responseService.saveResponse(response);
    }

    @GetMapping()
    public List<Response> getAllResponses() {
        return responseService.getAllResponses();
    }

    @GetMapping("/response-cards")
    public List<ResponseCard> getAllSurveyCards() {
        return responseService.getAllResponseCards();
    }

    @GetMapping("/{id}")
    public Response getResponseById(@PathVariable String id) {
        return responseService.getReponseById(id);
    }
}
