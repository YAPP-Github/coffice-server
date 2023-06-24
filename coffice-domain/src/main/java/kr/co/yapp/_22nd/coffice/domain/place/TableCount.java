package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TableCount implements Countable<TableCount, Integer> {
    @Column(name = "tableCount")
    private Integer value;

    public static TableCount from(Integer count) {
        TableCount tableCount = new TableCount();
        tableCount.value = count;
        return tableCount;
    }

    @Override
    public int compareTo(TableCount o) {
        return value.compareTo(o.value);
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
