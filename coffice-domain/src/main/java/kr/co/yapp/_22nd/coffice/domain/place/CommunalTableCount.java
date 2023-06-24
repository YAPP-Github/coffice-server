package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunalTableCount implements Comparable<CommunalTableCount> {
    @Column(name = "communalTableCount")
    private Integer value;

    public static CommunalTableCount from(Integer count) {
        CommunalTableCount communalTableCount = new CommunalTableCount();
        communalTableCount.value = count;
        return communalTableCount;
    }

    public boolean isPositive() {
        return value != null && value > 0;
    }

    @Override
    public int compareTo(CommunalTableCount o) {
        return value.compareTo(o.value);
    }


}
