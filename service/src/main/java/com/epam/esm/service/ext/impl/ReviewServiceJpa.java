package com.epam.esm.service.ext.impl;

import com.epam.esm.entity.Review;
import com.epam.esm.service.ext.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReviewServiceJpa implements ReviewService {

    @Override
    public List<Review> findAll(int offset, int limit) {
        return null;
    }

    @Override
    public Review find(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Review update(Review review, long id) {
        return null;
    }

    @Override
    public Review create(Review tag) {
        return null;
    }

    @Override
    public Review findById(long id) {
        return null;
    }


  /*  private final ReviewRepositoryJPA tagRepository;
    private final TagValidator tagValidator;
    private final PojoConverter<Review, Review> converter;

    @Autowired
    public ShopTagService(ReviewRepositoryJPA tagRepository, TagValidator tagValidator,
                          PojoConverter<Review, Review> converter) {
        this.tagRepository = tagRepository;
        this.tagValidator = tagValidator;
        this.converter = converter;
    }

    @Override
    public List<Review> findAll(int page, int size) {
        page = PojoConverter.convertPaginationPageToDbOffsetParameter(page, size);

        return converter.convert(tagRepository.findAll(--page, size));
    }

    @Override
    public Review find(long id) {
        tagValidator.checkId(id);
        return new Review(tagRepository.findById(id));
    }

    @Override
    public Review findMostWidelyUsedTag() {
        return new Review(tagRepository.findMostWidelyUsedTag());
    }

    @Override
    public void delete(long id) {
        tagValidator.checkId(id);
        tagRepository.delete(id);
    }

    @Override
    public Review create(Review tag) {
        tagValidator.isCorrectTag(tag);

        return new Review(tagRepository.create(converter.convert(tag)));
    }

    @Override
    public int getTagCount() {
        return tagRepository.getTagCount();
    }*/
}
