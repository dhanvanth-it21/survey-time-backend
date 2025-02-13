package com.trustrace.survey_time.controller;

import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;


    // ---------------------------------------------------------------------------------   Create Operations   ----------------------------------------------------------------------

    // Create a new survey
    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.saveSurvey(survey);
    }




    // ---------------------------------------------------------------------------------   Update Operations   ----------------------------------------------------------------------

    // Update the active status of a survey
    @PutMapping("/active-status/{surveyId}")
    public void updateActiveStatus(@PathVariable String surveyId) {
        surveyService.updateActiveStatus(surveyId);
    }




    // ---------------------------------------------------------------------------------   Read Operations   ----------------------------------------------------------------------

    // Get all surveys
    @GetMapping()
    public List<Survey> getAllSurveys(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        return surveyService.getAllSurveys(page, size);
    }

    // Get all survey cards
    @GetMapping("/survey-cards")
    public List<SurveyCard> getAllSurveyCards(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        return surveyService.getAllSurveyCards(page, size);
    }

    // Get survey by id
    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    // Get pending survey cards
    @GetMapping("/survey-cards/{emailId}")
    public List<SurveyCard> getPendingSurveyCards(@PathVariable String emailId) {
        return surveyService.getPendingSurveyCards(emailId);
    }




    // ---------------------------------------------------------------------------------   Delete Operations   ----------------------------------------------------------------------


    // Delete survey by id
    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurvey(id);
    }




}
