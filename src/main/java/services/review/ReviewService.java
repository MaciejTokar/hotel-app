package services.review;

import dao.*;
import model.Review;
import request.ReviewRequest;

public class ReviewService {

    private ReviewDao reviewDao;
    private ClientDao clientDao;
    private HotelDao hotelDao;

    public ReviewService(ReviewDao reviewDao, ClientDao clientDao, HotelDao hotelDao) {
        this.reviewDao = reviewDao;
        this.clientDao = clientDao;
        this.hotelDao = hotelDao;
    }

    public void saveReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        upsertReview(review, reviewRequest);

        reviewDao.save(review);
    }

    public void updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewDao.getById(id);
        upsertReview(review, reviewRequest);

        reviewDao.update(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewDao.getById(reviewId);
        reviewDao.delete(review);
    }

    private void upsertReview(Review review, ReviewRequest reviewRequest) {
        review.setOutcome(reviewRequest.getOutcome());
        review.setDescription(reviewRequest.getDescription());
        review.setDate(reviewRequest.getDate());
        review.setHotel(hotelDao.getById(reviewRequest.getHotelId()));
        review.setClient(clientDao.getById(reviewRequest.getClientId()));
    }
}
