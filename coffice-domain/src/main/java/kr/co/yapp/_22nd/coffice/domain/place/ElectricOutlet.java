package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ElectricOutlet {
    @Embedded
    private ElectricOutletCount count;

    @Enumerated(EnumType.STRING)
    @Column(name = "electricOutletLevel")
    private ElectricOutletLevel level;

    public static ElectricOutlet of(ElectricOutletCount electricOutletCount, SeatCount seatCount) {
        ElectricOutlet electricOutlet = new ElectricOutlet();
        electricOutlet.count = electricOutletCount;
        electricOutlet.level = ElectricOutletLevel.of(electricOutletCount, seatCount);
        return electricOutlet;
    }
}
