package kr.co.yapp.cafe.controller;

import kr.co.yapp.cafe.domain.scrapping.ScrappingResult;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.IntStream;

@RequestMapping("/scrapping-result")
@Controller
@RequiredArgsConstructor
public class ScrappingResultController {
    private final ScrappingResultService scrappingResultService;

    @GetMapping("/list")
    public String list(
            Pageable pageable,
            Model model
    ) {
        Page<ScrappingResult> page = scrappingResultService.findAll(pageable);
        model.addAttribute("scrappingResultPage", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.range(0, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "scrappingResult/index";
    }
}
