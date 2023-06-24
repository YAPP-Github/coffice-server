package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.OpeningHour;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.Data;

import java.time.OffsetTime;

@Data
public class OpeningHourResponse {
    private final String dayOfWeek;
    private final String openingHourType;
    private final OffsetTime openedAt;
    private final OffsetTime closedAt;

    public static OpeningHourResponse of(
            OpeningHour openingHour,
            DateTimeAssembler dateTimeAssembler
    ) {
        return new OpeningHourResponse(
                openingHour.getDayOfWeek().name(),
                openingHour.getOpeningHoursType().name(),
                dateTimeAssembler.toOffsetTime(openingHour.getOpenedAt()),
                dateTimeAssembler.toOffsetTime(openingHour.getClosedAt())
        );
    }
}
