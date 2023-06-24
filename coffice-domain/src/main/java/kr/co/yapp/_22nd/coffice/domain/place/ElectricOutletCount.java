package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 콘센트 정보
 */
@Embeddable
@Getter
@ToString
@EqualsAndHashCode
public class ElectricOutletCount implements Countable<ElectricOutletCount, Integer> {
    /**
     * 콘센트 개수
     */
    @Column(name = "electricOutletCount")
    private Integer value;

    public static ElectricOutletCount from(Integer count) {
        ElectricOutletCount electricOutletCount = new ElectricOutletCount();
        electricOutletCount.value = count;
        return electricOutletCount;
    }

    @Override
    public int compareTo(ElectricOutletCount o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean hasValue() {
        return value != null;
    }

    @Override
    public boolean isPositive() {
        return value != null && value > 0;
    }
}
