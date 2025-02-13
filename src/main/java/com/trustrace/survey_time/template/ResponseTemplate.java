package com.trustrace.survey_time.template;

import com.trustrace.survey_time.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponseTemplate {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Response save(Response response) {
        return mongoTemplate.save(response);
    }

    public List<Response> findAll() {
        return mongoTemplate.findAll(Response.class);
    }

    public Response findById(String id) {
        return mongoTemplate.findById(id, Response.class);
    }

    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Response.class);
    }

    public void deleteBySurveyId(String surveyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("surveyId").is(surveyId));
        mongoTemplate.remove(query, Response.class);
    }

    public List<Response> getAllResponsesByEmailId(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(emailId));
        List<Response> responses = mongoTemplate.find(query, Response.class);
        return responses;
    }

}
