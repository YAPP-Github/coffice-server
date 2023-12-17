package kr.co.yapp._22nd.coffice.ui.report;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.ReportApplicationService;
import kr.co.yapp._22nd.coffice.application.report.ReportImageUploadService;
import kr.co.yapp._22nd.coffice.infrastructure.naver.NaverApiClient;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.infrastructure.webhook.discord.DiscordWebhookService;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import kr.co.yapp._22nd.coffice.ui.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RequestMapping("/api/v1/reports")
@RestController
@RequiredArgsConstructor
public class ReportController {
    private final PlaceReportAssembler placeReportAssembler;
    private final ReportApplicationService reportApplicationService;
    private final DiscordWebhookService discordWebhookService;
    private final ReportImageUploadService reportImageUploadService;

    private final NaverApiClient naverApiClient;

    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PlaceReportResponse> createPlaceReport(
            @AuthenticationPrincipal Long memberId,
            @ModelAttribute @Valid PlaceReportCreateRequest placeReportCreateRequest
    ) {
        try {
            String streetAddress = placeReportCreateRequest.getStreetAddress();
            naverApiClient.getGeocode(streetAddress);

            PlaceReportResponse placeReportResponse = placeReportAssembler.toPlaceReportResponse(
                reportApplicationService.reportPlace(
                        memberId,
                        placeReportAssembler.toPlaceReportCreateVo(
                                placeReportCreateRequest,
                                reportImageUploadService.uploadReportImage(placeReportCreateRequest.getFiles()),
                                naverApiClient.getGeocode(placeReportCreateRequest.getStreetAddress())
                        )
                )
            );
            discordWebhookService.sendPlaceReportCreateMessage(placeReportResponse);
            return ApiResponse.success(placeReportResponse);
        } catch (IOException e) {
            return ApiResponse.failure(ResultCode.FAILURE, e.getMessage());
        }
    }
}