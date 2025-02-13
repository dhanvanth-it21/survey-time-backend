package com.trustrace.survey_time.dao.impl;

import com.trustrace.survey_time.dao.SurveyDAO;
import com.trustrace.survey_time.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyDAOImpl implements SurveyDAO {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Survey save(Survey survey) {
        return mongoTemplate.save(survey);
    }

    @Override
    public List<Survey> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, Survey.class);
    }

    @Override
    public List<Survey> findAll() {
        return mongoTemplate.findAll(Survey.class);
    }

    @Override
    public long count() {
        Query query = new Query();
        return mongoTemplate.count(query, Survey.class);
    }

    @Override
    public Survey findById(String id) {
        return mongoTemplate.findById(id, Survey.class);
    }

    @Override
    public List<Survey> getAllActiveSurveys() {
        Query query = new Query();
        query.addCriteria(Criteria.where("surveyObject.active").is(true));
        return mongoTemplate.find(query, Survey.class);
    }

    @Override
    public boolean existsById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Survey.class);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Survey.class);
    }


}
