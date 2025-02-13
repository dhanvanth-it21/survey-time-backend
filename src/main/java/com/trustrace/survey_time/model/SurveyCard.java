package com.trustrace.survey_time.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class SurveyCard {
    private String id;
    private String title;
    private String description;
    private Boolean active;


    public SurveyCard(String id, String title, String description, Boolean active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.active = active;
    }

    public SurveyCard() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}


