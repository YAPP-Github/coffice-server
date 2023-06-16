package kr.co.yapp._22nd.coffice.domain.scrapping;

import kr.co.yapp._22nd.coffice.domain.place.Address;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
@ToString
@EqualsAndHashCode
public class ScrappingResultCreateVo {
    String name;
    Address address;
    Coordinates coordinates;
    List<String> contactNumbers;
    List<String> imageUrls;
}
