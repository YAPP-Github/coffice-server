package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    /**
     * 도로명 주소
     */
    private final String streetAddress;
    /**
     * 지번 주소
     */
    private final String landAddress;
    /**
     * 우편번호
     */
    private final String postalCode;

    private Address(String streetAddress, String landAddress, String postalCode) {
        this.streetAddress = StringUtils.hasText(streetAddress) ? streetAddress.trim() : null;
        this.landAddress = StringUtils.hasText(landAddress) ? landAddress.trim() : null;
        this.postalCode = StringUtils.hasText(postalCode) ? postalCode.trim() : null;
    }

    protected Address() {
        this(null, null, null);
    }

    public String value() {
        return resolveAddress();
    }

    private String resolveAddress() {
        if (StringUtils.hasText(streetAddress)) {
            return streetAddress;
        }
        if (StringUtils.hasText(landAddress)) {
            return landAddress;
        }
        return null;
    }

    /**
     * 서울특별시 강남구 길이름/번지 -> 서울 강남
     */
    public String simpleValue() {
        String fullAddress = resolveAddress();
        if (fullAddress == null) {
            return null;
        }
        String[] split = fullAddress.split("\\s");
        List<String> candidates = new ArrayList<>();
        if (split.length >= 1) {
            String city = split[0].replace("서울특별시", "서울").trim();
            candidates.add(city);
        }
        if (split.length >= 2) {
            String district = split[1].trim();
            if (district.endsWith("구")) {
                candidates.add(district);
            }
        }
        return String.join(" ", candidates);
    }
}
