package services.review;

import dao.*;
import mapping.ReviewMapper;
import model.Review;
import request.ReviewRequest;
import response.ReviewResponse;

public class ReviewService {

    private ReviewDao reviewDao;
    private ClientDao clientDao;
    private HotelDao hotelDao;
    private ReviewMapper reviewMapper;

    public ReviewService(ReviewDao reviewDao, ClientDao clientDao, HotelDao hotelDao, ReviewMapper reviewMapper) {
        this.reviewDao = reviewDao;
        this.clientDao = clientDao;
        this.hotelDao = hotelDao;
        this.reviewMapper = reviewMapper;
    }

    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review = upsertReview(review, reviewRequest);

        reviewDao.save(review);

        return reviewMapper.fromReviewToReviewResponse(reviewDao.getById(review.getId()));
    }

    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewDao.getById(id);
        review = upsertReview(review, reviewRequest);

        reviewDao.update(review);

        return reviewMapper.fromReviewToReviewResponse(reviewDao.getById(review.getId()));
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewDao.getById(reviewId);
        reviewDao.delete(review);
    }

    private Review upsertReview(Review review, ReviewRequest reviewRequest) {
        return review.builder()
                .id(review.getId())
                .outcome(reviewRequest.getOutcome())
                .description(reviewRequest.getDescription())
                .date(reviewRequest.getDate())
                .hotel(hotelDao.getById(reviewRequest.getHotelId()))
                .client(clientDao.getById(reviewRequest.getClientId()))
                .build();
    }
}
