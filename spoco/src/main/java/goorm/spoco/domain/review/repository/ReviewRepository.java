package goorm.spoco.domain.review.repository;

import goorm.spoco.domain.review.domain.Review;
import goorm.spoco.domain.review.domain.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewId(Long reviewId);
    List<Review> findAllByCode_CodeIdAndReviewStatus(Long codeId, ReviewStatus reviewStatus);
}
