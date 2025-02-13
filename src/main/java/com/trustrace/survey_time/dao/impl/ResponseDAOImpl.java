package com.trustrace.survey_time.dao.impl;

import com.trustrace.survey_time.dao.ResponseDAO;
import com.trustrace.survey_time.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponseDAOImpl implements ResponseDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Response> getAllResponsesByEmailId(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(emailId));
        return mongoTemplate.find(query, Response.class);
    }

    @Override
    public Response save(Response response) {
        return mongoTemplate.save(response);
    }

    @Override
    public List<Response> findAll() {
        return mongoTemplate.findAll(Response.class);
    }

    @Override
    public Response findById(String id) {
        return mongoTemplate.findById(id, Response.class);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Response.class);
    }

    @Override
    public void deleteBySurveyId(String surveyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("surveyId").is(surveyId));
        mongoTemplate.remove(query, Response.class);
    }
}
