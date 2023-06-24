package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpeningHour {
    /**
     * 요일
     */
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    /**
     * 영업 여부
     */
    @Enumerated(EnumType.STRING)
    private OpeningHourType openingHoursType;
    /**
     * 정기, 비정기 휴일 여부
     */
    @Enumerated(EnumType.STRING)
    private OpeningHourSchedule schedule;
    /**
     * 24시간 영업 여부
     */
    private Boolean openAroundTheClock;
    /**
     * 오픈 시간
     */
    private LocalTime openedAt;
    /**
     * 마감 시간
     */
    private LocalTime closedAt;

    public static OpeningHour open(
            DayOfWeek dayOfWeek,
            Boolean openAroundTheClock,
            LocalTime openedAt,
            LocalTime closedAt
    ) {
        OpeningHour openingHour = new OpeningHour();
        openingHour.dayOfWeek = dayOfWeek;
        openingHour.schedule = OpeningHourSchedule.REGULAR;
        openingHour.openingHoursType = OpeningHourType.OPEN;
        openingHour.openAroundTheClock = openAroundTheClock;
        openingHour.openedAt = openedAt;
        openingHour.closedAt = closedAt;
        return openingHour;
    }

    public static OpeningHour closed(
            DayOfWeek dayOfWeek
    ) {
        OpeningHour openingHour = new OpeningHour();
        openingHour.dayOfWeek = dayOfWeek;
        openingHour.schedule = OpeningHourSchedule.REGULAR;
        openingHour.openingHoursType = OpeningHourType.CLOSED;
        openingHour.openAroundTheClock = null;
        openingHour.openedAt = null;
        openingHour.closedAt = null;
        return openingHour;
    }
}
