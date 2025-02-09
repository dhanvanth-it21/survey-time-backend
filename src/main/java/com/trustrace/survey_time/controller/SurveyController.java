package com.trustrace.survey_time.controller;

import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.saveSurvey(survey);
    }

    @GetMapping()
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/survey-cards")
    public List<SurveyCard> getAllSurveyCards() {
        return surveyService.getAllSurveyCards();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurvey(id);
    }
}
