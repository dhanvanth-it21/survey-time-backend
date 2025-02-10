package com.trustrace.survey_time.controller;


import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping()
    public Response createResponse(@RequestBody Response response) {
        return responseService.saveResponse(response);
    }
}
