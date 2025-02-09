package com.trustrace.survey_time.repository;


import com.trustrace.survey_time.model.Survey;
import com.trustrace.survey_time.model.SurveyCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends MongoRepository<Survey, String> {


}
