package com.trustrace.survey_time.service;

import com.trustrace.survey_time.builder.SurveyCardBuilder;
import com.trustrace.survey_time.dao.ResponseDAO;
import com.trustrace.survey_time.dao.SurveyDAO;
import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import com.trustrace.survey_time.template.ResponseTemplate;
import com.trustrace.survey_time.template.SurveyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SurveyService {


    @Autowired
    private SurveyDAO surveyDAO;

    @Autowired
    private ResponseDAO responseDAO;

    public Survey saveSurvey(Survey survey) {
        return surveyDAO.save(survey);
    }

    public Optional<Survey> getSurveyById(String id) {
        return Optional.ofNullable(surveyDAO.findById(id));
    }

    public boolean deleteSurvey(String id) {
        if (surveyDAO.existsById(id)) {
            surveyDAO.deleteById(id);
            return true;
        }
        return false; // Survey not found
    }


    public Page<SurveyCard> getAllSurveyCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Survey> surveys = surveyDAO.findAll(pageable);
        long totalCount = surveyDAO.count();

        List<SurveyCard> surveyCards = surveys.stream()
                .map(survey -> new SurveyCardBuilder()
                        .id(survey.getId())
                        .title((String) survey.getSurveyObject().get("title"))
                        .description((String) survey.getSurveyObject().get("description"))
                        .active((Boolean) survey.getSurveyObject().get("active"))
                        .build())
                .toList();

        return new PageImpl<>(surveyCards, pageable, totalCount);
    }

    public List<SurveyCard> getPendingSurveyCards(String emailId) {
        List<Survey> activeSurveys = surveyDAO.getAllActiveSurveys();
        Set<String> completedSurveyIds = responseDAO.getAllResponsesByEmailId(emailId)
                .stream()
                .map(Response::getSurveyId)
                .collect(Collectors.toSet());

        return activeSurveys.stream()
                .filter(survey -> !completedSurveyIds.contains(survey.getId()))
                .map(survey -> new SurveyCardBuilder()
                        .id(survey.getId())
                        .title((String) survey.getSurveyObject().get("title"))
                        .description((String) survey.getSurveyObject().get("description"))
                        .active(true)
                        .build())
                .toList();
    }

    public boolean updateActiveStatus(String surveyId) {
        Optional<Survey> surveyOptional = Optional.ofNullable(surveyDAO.findById(surveyId));

        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            Map<String, Object> surveyObject = survey.getSurveyObject();

            if (surveyObject != null) {
                Boolean isActive = (Boolean) surveyObject.get("active");
                surveyObject.put("active", !isActive);
                surveyDAO.save(survey);
                return true;
            }
        }
        return false;
    }
}