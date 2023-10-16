package kr.co.yapp._22nd.coffice.ui.report;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.ReportApplicationService;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.infrastructure.webhook.discord.DiscordWebhookService;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/reports")
@RestController
@RequiredArgsConstructor
public class ReportController {
    private final PlaceReportAssembler placeReportAssembler;
    private final ReportApplicationService reportApplicationService;
    private final DiscordWebhookService discordWebhookService;

    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping("/")
    public ApiResponse<PlaceReportResponse> createPlaceReport(
            @AuthenticationPrincipal Long memberId,
            @RequestBody @Valid PlaceReportCreateRequest placeReportCreateRequest
    ) {
        PlaceReportResponse placeReportResponse = placeReportAssembler.toPlaceReportResponse(
            reportApplicationService.reportPlace(
                    memberId,
                    placeReportAssembler.toPlaceReportCreateVo(placeReportCreateRequest)
            )
        );
        discordWebhookService.sendPlaceReportCreateMessage(placeReportResponse);
        return ApiResponse.success(placeReportResponse);
    }
}
