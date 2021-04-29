package com.epam.esm.service.support.impl;

import org.springframework.stereotype.Component;

@Component
public class TagPojoConverter /*implements PojoConverter<TagPOJO, Review>*/ {

   /* @Override
    public List<TagPOJO> convert(List<Review> reviews) {
        return reviews
            .stream()
            .map(TagPOJO::new)
            .collect(Collectors.toList());
    }

    @Override
    public Review convert(TagPOJO tag) {
        return new Review(tag.getId(), tag.getName());
    }*/
}
