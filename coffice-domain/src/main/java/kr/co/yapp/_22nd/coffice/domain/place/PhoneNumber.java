package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    @Column(name = "phoneNumber")
    private String value;

    public static PhoneNumber from(String phoneNumber) {
        PhoneNumber instance = new PhoneNumber();
        instance.value = phoneNumber;
        return instance;
    }
    
}
