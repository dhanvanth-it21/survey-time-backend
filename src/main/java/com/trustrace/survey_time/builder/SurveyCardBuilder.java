package com.trustrace.survey_time.builder;

import com.trustrace.survey_time.model.SurveyCard;

public class SurveyCardBuilder {
    private String id;
    private String title;
    private String description;
    private Boolean active;

    public SurveyCardBuilder id(String id) {
        this.id = id;
        return this;
    }

    public SurveyCardBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SurveyCardBuilder description(String description) {
        this.description = description;
        return this;
    }

    public SurveyCardBuilder active(Boolean active) {
        this.active = active;
        return this;
    }

    public SurveyCard build() {
        return new SurveyCard(id, title, description, active);
    }
}
