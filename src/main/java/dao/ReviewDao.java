package dao;

import exeption.ErrorCode;
import exeption.ReviewException;
import model.Review;

import java.util.Optional;

public class ReviewDao extends CommonDao<Review> {
    public ReviewDao() {
        super(Review.class);
    }

    @Override
    public Review getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Review.class, id)))
                .orElseThrow(() -> new ReviewException(ErrorCode.REVIEW_ID_EXCEPTION, String.valueOf(id)));
    }
}
