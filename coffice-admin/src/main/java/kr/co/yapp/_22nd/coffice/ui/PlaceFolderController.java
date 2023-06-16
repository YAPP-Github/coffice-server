package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/place-folder")
@Controller
@RequiredArgsConstructor
public class PlaceFolderController {
    private final PlaceFolderService placeFolderService;

    @GetMapping
    public String list(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        model.addAttribute("placeFolderPage", placeFolderService.findAll(pageable));
        return "place-folder/list";
    }

    @GetMapping("/{placeFolderId}")
    public String detail(
            @PathVariable Long placeFolderId,
            Model model
    ) {
        model.addAttribute("placeFolder", placeFolderService.getPlaceFolder(placeFolderId));
        return "place-folder/detail";
    }
}
