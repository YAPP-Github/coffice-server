package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

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
        if (StringUtils.hasText(streetAddress)) {
            return streetAddress;
        }
        if (StringUtils.hasText(landAddress)) {
            return landAddress;
        }
        return null;
    }
}
