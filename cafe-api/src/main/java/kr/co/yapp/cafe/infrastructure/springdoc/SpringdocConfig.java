package kr.co.yapp.cafe.infrastructure.springdoc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@SecurityScheme(
        name = SpringdocConfig.SECURITY_SCHEME_NAME,
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER,
        description = "'value' 에 accessToken 값만 입력하면 됩니다."
)
@Configuration
public class SpringdocConfig {
    public static final String SECURITY_SCHEME_NAME = "Bearer Authentication";
}
