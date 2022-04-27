package com.cydeo.pojoZipCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Place {

    @JsonProperty("place name")
    private String placeName;
    private String state;
    private String latitude;


}
