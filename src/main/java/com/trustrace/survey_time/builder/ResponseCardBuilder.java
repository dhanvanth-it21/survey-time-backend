package com.trustrace.survey_time.builder;

import com.trustrace.survey_time.model.ResponseCard;

public class ResponseCardBuilder {
    private String id;
    private String surveyId;
    private String name;
    private String email;
    private String surveyTitle;

    public ResponseCardBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ResponseCardBuilder surveyId(String surveyId) {
        this.surveyId = surveyId;
        return this;
    }

    public ResponseCardBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ResponseCardBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ResponseCardBuilder surveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
        return this;
    }

    public ResponseCard build() {
        return new ResponseCard(id, surveyId, name, email, surveyTitle);
    }
}