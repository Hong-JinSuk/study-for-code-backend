package goorm.spoco.domain.review.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ReviewResponseDto(

        Long reviewId,
        Integer codeLine,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String reviewStatus
) {
}
