package se.yrgo.data;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface ReviewDao {
    public List<Review> allReviews();

    public void createReview(Review newReview);

    public void deleteReview(Review review);

    public void updateReviewById(int id, Review review) throws ReviewNotFoundException;

}
