package kr.co.yapp._22nd.coffice.domain.review;

import kr.co.yapp._22nd.coffice.domain.CofficeException;

public class ReviewNotFoundException extends CofficeException {
    public ReviewNotFoundException(Long reviewId) {
        super("Review not found. reviewId: " + reviewId);
    }
}
