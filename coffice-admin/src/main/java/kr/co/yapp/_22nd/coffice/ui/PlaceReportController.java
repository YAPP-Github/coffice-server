package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/placeReport")
@Controller
@RequiredArgsConstructor
public class PlaceReportController {
    private final PlaceReportService placeReportService;
    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
            ) {
        model.addAttribute("placeReportPage", placeReportService.findAll(pageable));
        return "placeReport/list";
    }

    @GetMapping("/{placeReportId}")
    public String detail(
            @PathVariable Long placeReportId,
            Model model
    ) {
        model.addAttribute("placeReport", placeReportService.getPlaceReport(placeReportId));
        return "placeReport/detail";
    }
}
