package com.trustrace.survey_time.controller;

import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;


    // ----------------------------------   Create Operations   ----------------------------------------------------------------------

    // Create a new survey
    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        Survey savedSurvey = surveyService.saveSurvey(survey);
        return ResponseEntity.ok(savedSurvey);
    }




    // --------------------------------   Update Operations   ----------------------------------------------------------------------

    // Update the active status of a survey
    @PutMapping("/active-status/{surveyId}")
    public ResponseEntity<String> updateActiveStatus(@PathVariable String surveyId) {
        boolean updated = surveyService.updateActiveStatus(surveyId);
        return updated
                ? ResponseEntity.ok("Survey active status updated successfully")
                : ResponseEntity.status(404).body("Survey not found");
    }



    // -----------------------------------   Read Operations   ----------------------------------------------------------------------

    // Get all surveys
    //not used
//    @GetMapping()
//    public List<Survey> getAllSurveys(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
//        return surveyService.getAllSurveys(page, size);
//    }

    // Get all survey cards
    @GetMapping("/survey-cards")
    public ResponseEntity<Page<SurveyCard>> getAllSurveyCards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        return ResponseEntity.ok(surveyService.getAllSurveyCards(page, size));
    }

    // Get survey by id
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable String id) {
        Survey survey = surveyService.getSurveyById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        return ResponseEntity.ok(survey);
    }

    // Get pending survey cards
    @GetMapping("survey-cards/{emailId}")
    public ResponseEntity<List<SurveyCard>> getPendingSurveyCards(@PathVariable String emailId) {
        List<SurveyCard> pendingSurveys = surveyService.getPendingSurveyCards(emailId);
        return ResponseEntity.ok(pendingSurveys);
    }




    // -------------------------------   Delete Operations   ----------------------------------------------------------------------


    // Delete survey by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable String id) {
        boolean isDeleted = surveyService.deleteSurvey(id);
        return isDeleted
                ? ResponseEntity.ok("Survey deleted successfully")
                : ResponseEntity.status(404).body("Survey not found");
    }





}
