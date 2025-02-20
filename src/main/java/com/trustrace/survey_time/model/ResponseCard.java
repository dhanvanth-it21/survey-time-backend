package com.trustrace.survey_time.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCard {
    private String id;
    private String surveyId;
    private String name;
    private String email;
    private String surveyTitle;

    public String getId() {
        return id;
    }

    public ResponseCard(String id, String surveyId, String name, String email, String surveyTitle) {
        this.id = id;
        this.surveyId = surveyId;
        this.name = name;
        this.email = email;
        this.surveyTitle = surveyTitle;
    }

    public ResponseCard() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }
}
