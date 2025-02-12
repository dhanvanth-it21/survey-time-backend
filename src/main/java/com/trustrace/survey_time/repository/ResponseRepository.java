package com.trustrace.survey_time.repository;

import com.trustrace.survey_time.model.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends MongoRepository<Response, String> {
    void deleteBySurveyId(String surveyId);

}
