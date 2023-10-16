package kr.co.yapp._22nd.coffice.domain.place.report;

public interface PlaceReportService {
    PlaceReport create(Long memberId, PlaceReportCreateVo placeReportCreateVo);
}
