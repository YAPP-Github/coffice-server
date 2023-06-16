package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/place")
@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        Page<Place> placePage = placeService.findAll(pageable);
        model.addAttribute("placePage", placePage);
        return "place/list";
    }

    @GetMapping("/{placeId}")
    public String detail(
            @PathVariable Long placeId,
            Model model
    ) {
        model.addAttribute("place", placeService.getPlace(placeId));
        return "place/detail";
    }

}
