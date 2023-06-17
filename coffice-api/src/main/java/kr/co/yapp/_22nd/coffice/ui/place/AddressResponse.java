package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Address;
import lombok.Data;

@Data
public class AddressResponse {
    private final String value;
    private final String postalCode;

    public static AddressResponse from(Address address) {
        return new AddressResponse(
                address.value(),
                address.getPostalCode()
        );
    }
}
