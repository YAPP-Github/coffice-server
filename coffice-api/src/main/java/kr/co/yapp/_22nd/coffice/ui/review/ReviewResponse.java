package kr.co.yapp._22nd.coffice.ui.review;


import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.ui.member.MemberResponse;

import java.time.OffsetDateTime;

public record ReviewResponse(
        @NotNull
        Long reviewId,
        @NotNull
        MemberResponse member,
        @NotNull
        String electricOutletLevel,
        @NotNull
        String wifiLevel,
        @NotNull
        String noiseLevel,
        String content,
        @NotNull
        OffsetDateTime createdAt,
        @NotNull
        OffsetDateTime updatedAt
) {
}
