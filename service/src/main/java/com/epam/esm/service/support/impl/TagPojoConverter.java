package com.epam.esm.service.support.impl;

import com.epam.esm.entity.Review;
import com.epam.esm.pojo.TagPOJO;
import com.epam.esm.service.support.PojoConverter;
import java.util.List;
import java.util.stream.Collectors;
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
