package kr.co.yapp._22nd.coffice.domain.place;


import lombok.Value;

@Value(staticConstructor = "of")
public class PlaceQueryRequestVo {
    String name;
}
