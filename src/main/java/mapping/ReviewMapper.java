package mapping;

import model.Review;
import response.ReviewResponse;

public class ReviewMapper {

    public ReviewResponse fromReviewToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .outcome(review.getOutcome())
                .description(review.getDescription())
                .date(review.getDate())
                .hotelId(review.getHotel().getId())
                .clientId(review.getClient().getId())
                .build();
    }
}
