package kr.co.yapp.cafe.ui.place;

import kr.co.yapp.cafe.domain.place.*;
import kr.co.yapp.cafe.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/places")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceAssembler placeAssembler;

    @GetMapping
    public ApiResponse<List<PlaceResponse>> getPlaces(
            @PageableDefault Pageable pageable
    ) {
        return ApiResponse.success(
                placeService.findAll(pageable)
                        .map(placeAssembler::toPlaceResponse)
        );
    }

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceResponse> getPlace(
            @PathVariable Long placeId
    ) {
        return ApiResponse.success(
                placeService.findById(placeId)
                        .map(placeAssembler::toPlaceResponse)
                        .orElseThrow(() -> new PlaceNotFoundException(placeId))
        );
    }

    @PostMapping
    public ApiResponse<PlaceResponse> createPlace(
            @RequestBody PlaceCreateRequest placeCreateRequest
    ) {
        PlaceCreateVo placeCreateVo = placeAssembler.toPlaceCreateVo(placeCreateRequest);
        Place place = placeService.create(placeCreateVo);
        PlaceResponse placeResponse = placeAssembler.toPlaceResponse(place);
        return ApiResponse.success(placeResponse);
    }

    @PutMapping("/{placeId}")
    public ApiResponse<PlaceResponse> updatePlace(
            @PathVariable Long placeId,
            @RequestBody PlaceUpdateRequest placeUpdateRequest
    ) {
        PlaceUpdateVo placeUpdateVo = placeAssembler.toPlaceUpdateVo(placeUpdateRequest);
        Place place = placeService.update(placeId, placeUpdateVo);
        PlaceResponse placeResponse = placeAssembler.toPlaceResponse(place);
        return ApiResponse.success(placeResponse);
    }
}
