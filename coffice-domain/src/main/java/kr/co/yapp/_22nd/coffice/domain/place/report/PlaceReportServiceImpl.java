package kr.co.yapp._22nd.coffice.domain.place.report;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceReportServiceImpl implements PlaceReportService{
    private final MemberRepository memberRepository;
    private final PlaceReportRepository placeReportRepository;
    @Override
    public PlaceReport create(
            Long memberId,
            PlaceReportCreateVo placeReportCreateVo
    ) {
        /* TODO : validate placeReportCreateVo */
        Member member = memberRepository.getReferenceById(memberId);
        PlaceReport placeReport = PlaceReport.of(member, placeReportCreateVo);
        return placeReportRepository.save(placeReport);
    }
}
