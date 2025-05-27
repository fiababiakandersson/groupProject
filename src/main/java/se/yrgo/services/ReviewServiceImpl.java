package se.yrgo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

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

    @Override
    public Review getReviewById(int id) throws ReviewNotFoundException {
        Review r = reviewDao.findReviewById(id);
        if (r == null) {
            throw new ReviewNotFoundException();
        }
        return r;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.allReviews();
    }

    

}
