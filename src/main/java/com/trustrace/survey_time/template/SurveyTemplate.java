package com.trustrace.survey_time.template;


import com.trustrace.survey_time.model.Response;
import com.trustrace.survey_time.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Survey save(Survey survey) {
        return mongoTemplate.save(survey);
    }

    public List<Survey> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, Survey.class);
    }

    public List<Survey> findAll() {
        return mongoTemplate.findAll(Survey.class);
    }

    public Survey findById(String id) {
        return  mongoTemplate.findById(id, Survey.class);
    }

    public void deleteById(String id) {
       Query query = new Query();
       query.addCriteria(Criteria.where("id").is(id));
       mongoTemplate.remove(query, Survey.class);
    }

    public List<Survey> getAllActiveSurveys() {
        Query query = new Query();
        query.addCriteria(Criteria.where("surveyObject.active").is(true));
        return mongoTemplate.find(query, Survey.class);
    }




}
