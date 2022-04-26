package com.cydeo.pojoHR;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.util.List;

@Getter
@Setter
@ToString

@Data
//  everything will be added in one short





public class Region {

     @JsonProperty("region_id")
     private int regionId ;
     @JsonProperty("region_id")
     private String regionName;
     private List<Link> links;




}
