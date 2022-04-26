package com.cydeo.pojoSpartans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString


public class SearchSpartans {

    private List<Spartan> content;
    private int totalElement ;

    public List<Spartan> getContent() {
        return content;
    }


}
