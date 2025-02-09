package com.trustrace.survey_time.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "surveys")
public class Survey {
    @Id
    private String id;

    private Map<String, Object> surveyObject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Map<String, Object> getSurveyObject() {
        return surveyObject;
    }

    public void setSurveyObject(Map<String, Object> surveyObject) {
        this.surveyObject = surveyObject;
    }
}
