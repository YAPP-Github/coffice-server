package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.PlaceUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/place")
@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceApplicationService placeApplicationService;

    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        Page<Place> placePage = placeApplicationService.findAll(pageable);
        model.addAttribute("placePage", placePage);
        return "place/list";
    }

    @GetMapping("/{placeId}")
    public String detail(
            @PathVariable Long placeId,
            Model model
    ) {
        model.addAttribute("place", placeApplicationService.getPlace(placeId));
        return "place/detail";
    }

    @GetMapping("/add")
    public String addForm() {
        return "place/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute PlaceAddRequest placeAddRequest,
            Model model
    ) {
        Place place = placeApplicationService.create(
                PlaceCreateVo.of(
                        placeAddRequest.getName(),
                        Coordinates.of(
                                placeAddRequest.getLatitude(),
                                placeAddRequest.getLongitude()
                        )
                )
        );
        model.addAttribute("place", place);
        return "redirect:/place/" + place.getPlaceId();
    }

    @GetMapping("/{placeId}/edit")
    public String editForm(
            @PathVariable Long placeId,
            Model model
    ) {
        model.addAttribute("place", placeApplicationService.getPlace(placeId));
        return "place/edit";
    }

    @PostMapping("/{placeId}/edit")
    public String edit(
            @PathVariable Long placeId,
            @ModelAttribute PlaceEditRequest placeEditRequest,
            Model model
    ) {
        Place place = placeApplicationService.update(
                placeId,
                PlaceUpdateVo.of(
                        placeEditRequest.getName(),
                        Coordinates.of(
                                placeEditRequest.getLatitude(),
                                placeEditRequest.getLongitude()
                        )
                )
        );
        model.addAttribute("place", place);
        return "redirect:/place/" + placeId;
    }

}
