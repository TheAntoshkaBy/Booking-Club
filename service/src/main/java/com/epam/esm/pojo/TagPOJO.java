package com.epam.esm.pojo;

import com.epam.esm.entity.Review;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class TagPOJO {

    private Long id;
    private String name;

    public TagPOJO(Review review) {
       // this.id = review.getId();
/*
        this.name = review.getName();
*/
    }

    public TagPOJO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
