package services.review;

import dao.ClientDao;
import dao.HotelDao;
import dao.ReviewDao;
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

        reviewDao.saveReview(review);
    }

    public void updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewDao.getReview(id);
        upsertReview(review, reviewRequest);

        reviewDao.updateReview(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewDao.getReview(reviewId);
        reviewDao.deleteReview(review);
    }

    private void upsertReview(Review review, ReviewRequest reviewRequest) {
        review.setOutcome(reviewRequest.getOutcome());
        review.setDescription(reviewRequest.getDescription());
        review.setDate(reviewRequest.getDate());
        review.setHotel(hotelDao.getHotel(reviewRequest.getHotelId()));
        review.setClient(clientDao.getClient(reviewRequest.getClientId()));
    }
}
