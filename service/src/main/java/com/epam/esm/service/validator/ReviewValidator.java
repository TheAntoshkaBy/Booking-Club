package com.epam.esm.service.validator;

import com.epam.esm.entity.Review;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    private final ReviewRepository reviewRepository;
    private List<InvalidDataMessage> invalidDataMessageList;

    @Autowired
    public ReviewValidator(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void isCorrectReview(Review review) {
        invalidDataMessageList = new ArrayList<>();
        checkNameUnique(review.getTopic());
        if (!invalidDataMessageList.isEmpty()) {
            throw new ServiceValidationException(invalidDataMessageList);
        }
    }

    private void checkNameUnique(String name) {
      /*if (reviewRepository.findByName(name) != null) {
            invalidDataMessageList.add(
                new InvalidDataMessage(ErrorTextMessageConstants.review_NAME_FIELD_IS_EXIST));
        }*/
    }

    public void checkId(Long id) {
        if (id <= 0) {
            throw new ServiceValidationException(new InvalidDataMessage(""));
        }
    }
}
