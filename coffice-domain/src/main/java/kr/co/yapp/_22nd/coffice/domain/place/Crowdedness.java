package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crowdedness {
    @Enumerated(EnumType.STRING)
    private WeekDayType weekDayType;
    @Enumerated(EnumType.STRING)
    private DayTimeType dayTimeType;
    @Enumerated(EnumType.STRING)
    private CrowdednessLevel crowdednessLevel;

    public static Crowdedness of(
            WeekDayType weekDayType,
            DayTimeType dayTimeType,
            CrowdednessLevel crowdednessLevel
    ) {
        Crowdedness crowdedness = new Crowdedness();
        crowdedness.weekDayType = weekDayType;
        crowdedness.dayTimeType = dayTimeType;
        crowdedness.crowdednessLevel = crowdednessLevel;
        return crowdedness;
    }
}
