package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatCount implements Comparable<SeatCount> {

    @Column(name = "seatCount")
    private Integer value;

    public static SeatCount from(Integer count) {
        SeatCount seatCount = new SeatCount();
        seatCount.value = count;
        return seatCount;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean hasValue() {
        return value != null;
    }

    @Override
    public int compareTo(SeatCount o) {
        return this.value.compareTo(o.value);
    }
}
