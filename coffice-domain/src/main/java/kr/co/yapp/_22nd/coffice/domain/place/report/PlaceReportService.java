package kr.co.yapp._22nd.coffice.domain.place.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceReportService {
    PlaceReport create(Long memberId, PlaceReportCreateVo placeReportCreateVo);
    Page<PlaceReport> findAll(Pageable pageable);
    PlaceReport getPlaceReport(Long placeReportId);
}
