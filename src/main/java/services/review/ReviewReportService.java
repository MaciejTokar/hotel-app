package services.review;

import dao.ReviewDao;
import mapping.ReviewMapper;
import model.Review;
import response.ReviewResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReviewReportService {

    private ReviewDao reviewDao;
    private ReviewMapper reviewMapper;

    public ReviewReportService(ReviewDao reviewDao, ReviewMapper reviewMapper) {
        this.reviewDao = reviewDao;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewResponse> searchReview(Long clientId) {
        return reviewDao.findAll().stream()
                .filter(r -> r.getClient().getId().equals(clientId))
                .map(r -> reviewMapper.fromReviewToReviewResponse(r))
                .toList();
    }

    public List<ReviewResponse> sortedReview() {
        return reviewDao.findAll().stream()
                .map(reviewMapper::fromReviewToReviewResponse)
                .sorted(Comparator.comparing(ReviewResponse::getOutcome).reversed().thenComparing(ReviewResponse::getDate))
                .toList();
    }

    public Map<String, Double> avgOutcome() {
        return reviewDao.findAll().stream()
                .collect(Collectors.groupingBy(r -> r.getHotel().getName(), Collectors.averagingDouble(o -> o.getOutcome().doubleValue())));
    }

    public Map<Long, List<Integer>> clientOutcome() {
        return reviewDao.findAll().stream()
                .collect(Collectors.groupingBy(o -> o.getClient().getId(), Collectors.mapping(Review::getOutcome, Collectors.toList())));
    }
}
