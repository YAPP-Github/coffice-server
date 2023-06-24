package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceCreateVo {
    String name;
    Coordinates coordinates;
    Address address;
    List<OpeningHour> openingHours;
    SeatCount seatCount;
    ElectricOutletCount electricOutletCount;
    CommunalTableCount communalTableCount;
}
