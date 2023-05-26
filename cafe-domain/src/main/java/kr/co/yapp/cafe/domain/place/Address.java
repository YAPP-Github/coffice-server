package kr.co.yapp.cafe.domain.place;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Embeddable
@Builder
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
        this.streetAddress = streetAddress != null ? streetAddress.trim() : null;
        this.landAddress = landAddress != null ? landAddress.trim() : null;
        this.postalCode = postalCode != null ? postalCode.trim() : null;
    }

    protected Address() {
        this(null, null, null);
    }
}
