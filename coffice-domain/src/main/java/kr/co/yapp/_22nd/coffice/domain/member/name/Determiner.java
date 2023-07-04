package kr.co.yapp._22nd.coffice.domain.member.name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Determiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long determinerId;
    private String value;
}
