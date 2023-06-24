package kr.co.yapp._22nd.coffice.ui;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.TimeZone;

@Component
public class DateTimeAssembler {
    public OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, resolveZoneOffset());
    }

    public OffsetTime toOffsetTime(LocalTime localTime) {
        return OffsetTime.of(localTime, resolveZoneOffset());
    }

    private ZoneOffset resolveZoneOffset() {
        TimeZone timeZone = LocaleContextHolder.getTimeZone();
        return ZoneOffset.ofTotalSeconds(timeZone.getRawOffset() / 1000);
    }
}
