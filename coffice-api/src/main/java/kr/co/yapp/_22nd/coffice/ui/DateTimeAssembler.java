package kr.co.yapp._22nd.coffice.ui;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Component
public class DateTimeAssembler {
    public OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        TimeZone timeZone = LocaleContextHolder.getTimeZone();
        return OffsetDateTime.of(localDateTime, ZoneOffset.ofTotalSeconds(timeZone.getRawOffset() / 1000));
    }
}
