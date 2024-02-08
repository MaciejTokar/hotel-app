package dao;

import model.Review;

public class ReviewDao extends CommonDao<Review> {
    public ReviewDao() {
        super(Review.class);
    }

    @Override
    public void save(Review review) {
        executeInTransaction(session -> session.save(review));
        if (review.getClient().getId() == null) {
            throw new NullPointerException();
        }

        if (review.getHotel().getId() == null) {
            throw new NullPointerException();
        }
    }
}
