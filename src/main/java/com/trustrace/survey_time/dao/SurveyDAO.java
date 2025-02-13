package com.trustrace.survey_time.dao;

import com.trustrace.survey_time.model.Survey;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyDAO {
    //save the survey to the DB
    Survey save(Survey survey);

    //get all the surveys from the DB with pagination
    List<Survey> findAll(Pageable pageable);

    List<Survey> findAll();

    //get the total count of the surveys
    long count();

    Survey findById(String id);

    List<Survey> getAllActiveSurveys();

    boolean existsById(String id);

    void deleteById(String id);
}
