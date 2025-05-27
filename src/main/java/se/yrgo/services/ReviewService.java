package se.yrgo.services;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface ReviewService {
    // List<Review> getAllReviews();

    void addReview(Review review);

    void deleteReview(Review review);

    void updateReview(int id, Review review) throws ReviewNotFoundException;
}
