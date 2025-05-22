package se.yrgo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.allReviews();
    }

    @Override
    public void addReview(Review review) {
        reviewDao.createReview(review);
    }

    @Override
    public void deleteReview(Review review) {
        reviewDao.deleteReview(review);
    }

    @Override
    public void updateReview(int id, Review review) throws ReviewNotFoundException {
        try {
            reviewDao.updateReviewById(id, review);
        } catch (Exception e) {
            throw new ReviewNotFoundException();
        }
    }

}
