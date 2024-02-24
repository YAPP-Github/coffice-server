package kr.co.yapp._22nd.coffice.ui.PlaceReport;

import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.GeoCodingService;
import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReport;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/placeReport")
@Controller
@RequiredArgsConstructor
public class PlaceReportController {
    private final PlaceReportService placeReportService;
    private final PlaceApplicationService placeApplicationService;
    private final GeoCodingService geoCodingService;
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

    @GetMapping("/{placeReportId}/register")
    public String registerForm(
            @PathVariable Long placeReportId,
            Model model
    ) {
        PlaceReport placeReport = placeReportService.getPlaceReport(placeReportId);
        model.addAttribute("placeReport", placeReport);
        model.addAttribute("foodTypes", FoodType.values());
        model.addAttribute("restroomTypes", RestroomType.values());
        model.addAttribute("drinkTypes", DrinkType.values());

        /* TODO : Electric Outlet Count, Seat Count, Table Count, CommunalTable Count 누락값 처리 로직 수정 */
        int tableCount = placeReport.getCapacityLevel().getFrom();
        int seatCount = 2*tableCount, electricOutletCount;
        model.addAttribute("tableCount", tableCount);
        model.addAttribute("communalTableCount", placeReport.getHasCommunalTable() ? 1 : 0);

        if(placeReport.getElectricOutletLevel().equals(ElectricOutletLevel.FEW)) {
            electricOutletCount = (int)(seatCount * (0 + 0.25) / 2);
        } else if (placeReport.getElectricOutletLevel().equals(ElectricOutletLevel.SEVERAL)){
            electricOutletCount = (int)(seatCount * (0.25 + 0.4) / 2);
        } else if (placeReport.getElectricOutletLevel().equals(ElectricOutletLevel.MANY)){
            electricOutletCount = (int)(seatCount * (0.4 + 1.0) / 2);
        } else {
            // UNKNOWN
            electricOutletCount = 0;
        }
        model.addAttribute("seatCount", seatCount);
        model.addAttribute("electricOutletCount", electricOutletCount);
        return "placeReport/register";
    }

    @PostMapping("/{placeReportId}/register")
    public String register(
            @PathVariable Long placeReportId,
            @ModelAttribute PlaceReportAddRequest placeReportAddRequest,
            Model model
    ) {
        Place place = placeApplicationService.create(
                PlaceCreateVo.of(
                        placeReportAddRequest.getName(),
                        resolveCoordinates(placeReportAddRequest),
                        Address.builder()
                                .streetAddress(placeReportAddRequest.getStreetAddress())
                                .landAddress(placeReportAddRequest.getLandAddress())
                                .postalCode(placeReportAddRequest.getPostalCode())
                                .build(),
                        Collections.emptyList(),
                        PhoneNumber.from(placeReportAddRequest.getPhoneNumber()),
                        placeReportAddRequest.getHomepageUrl(),
                        ElectricOutletCount.from(placeReportAddRequest.getElectricOutletCount()),
                        SeatCount.from(placeReportAddRequest.getSeatCount()),
                        TableCount.from(placeReportAddRequest.getTableCount()),
                        CommunalTableCount.from(placeReportAddRequest.getCommunalTableCount()),
                        placeReportAddRequest.getImageUrls(), //imageUrls
                        Collections.emptyList(), //Crowdedness
                        Optional.ofNullable(placeReportAddRequest.getDrinkTypes())
                                .map(drinkTypes -> drinkTypes.stream()
                                    .map(DrinkType::valueOf)
                                    .collect(Collectors.toList()))
                                .orElse(Collections.emptyList()), //DrinkType
                        Optional.ofNullable(placeReportAddRequest.getFoodTypes())
                                .map(foodTypes -> foodTypes.stream()
                                    .map(FoodType::valueOf)
                                    .collect(Collectors.toList()))
                                .orElse(Collections.emptyList()), //FoodType
                        Optional.ofNullable(placeReportAddRequest.getRestroomTypes())
                                .map(restroomTypes -> restroomTypes.stream()
                                    .map(RestroomType::valueOf)
                                    .collect(Collectors.toList()))
                                .orElse(Collections.emptyList())//RestroomType
                )
        );
        placeReportService.register(placeReportId, place.getPlaceId());
        model.addAttribute("place", place);
        return "redirect:/place/"+place.getPlaceId();
    }

    private Coordinates resolveCoordinates(PlaceReportAddRequest placeAddRequest) {
        if (placeAddRequest.getLatitude() != null
                && placeAddRequest.getLongitude() != null) {
            return Coordinates.of(
                    placeAddRequest.getLatitude(),
                    placeAddRequest.getLongitude()
            );
        }
        String address = placeAddRequest.getStreetAddress() != null
                ? placeAddRequest.getStreetAddress()
                : placeAddRequest.getLandAddress();
        return geoCodingService.getCoordinates(address);
    }
}
