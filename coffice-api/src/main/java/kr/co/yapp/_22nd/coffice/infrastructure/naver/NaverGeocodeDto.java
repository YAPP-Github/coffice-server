package kr.co.yapp._22nd.coffice.infrastructure.naver;
import lombok.Data;
import java.util.List;

@Data
public class NaverGeocodeDto {
    private List<NaverGeocodeAddressDto> addresses;
}
