package com.trustrace.survey_time.dao;

import com.trustrace.survey_time.model.Response;

import java.util.List;
import java.util.Optional;

public interface ResponseDAO {
    List<Response> getAllResponsesByEmailId(String emailId);

    Response save(Response response);

    List<Response> findAll();

    Response findById(String id);

    void deleteById(String id);

    void deleteBySurveyId(String surveyId);
}
