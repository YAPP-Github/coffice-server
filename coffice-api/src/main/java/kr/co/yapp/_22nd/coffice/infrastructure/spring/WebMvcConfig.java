package kr.co.yapp._22nd.coffice.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class WebMvcConfig {
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(Locale.KOREA, TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")));
    }
}
