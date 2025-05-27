package se.yrgo.services;

import se.yrgo.domain.*;
import se.yrgo.exception.*;
import java.util.List;

public interface ReviewService {

    Review getReviewById(int id) throws ReviewNotFoundException;

    void addReview(Review review);

    void deleteReview(Review review);

    void updateReview(int id, Review review) throws ReviewNotFoundException;

    List<Review> getAllReviews();
}
