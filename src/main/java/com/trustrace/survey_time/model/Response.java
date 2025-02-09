package com.trustrace.survey_time.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "responses")
public class Response {
    private String id;

    private Map<String,Object> responseObject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Map<String, Object> responseObject) {
        this.responseObject = responseObject;
    }
}
