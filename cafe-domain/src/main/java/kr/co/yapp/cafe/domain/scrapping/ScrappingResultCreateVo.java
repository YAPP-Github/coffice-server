package kr.co.yapp.cafe.domain.scrapping;

import kr.co.yapp.cafe.domain.place.Address;
import kr.co.yapp.cafe.domain.place.Coordinates;
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
