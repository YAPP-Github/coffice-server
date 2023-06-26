package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.place.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PlaceQueryService placeQueryService;

    @GetMapping
    public String index(
            Model model
    ) {
        Page<PlaceSearchResponseVo> placeSearchResponseVoPage = placeQueryService.search(
                PlaceSearchRequestVo.of(
                        "",
                        Coordinates.of(
                                37.5015587,
                                127.026319
                        ),
                        Distance.of(1.0, DistanceUnit.KILOMETER),
                        null,
                        null,
                        Collections.emptySet(),
                        Collections.emptySet(),
                        Collections.emptySet(),
                        Collections.emptySet()
                ),
                Pageable.unpaged()
        );
        model.addAttribute("places", placeSearchResponseVoPage.getContent());
        model.addAttribute("message", "hello");
        return "home";
    }
}
