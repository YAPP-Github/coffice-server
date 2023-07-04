package kr.co.yapp._22nd.coffice.domain.member.name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nounId;
    private String value;
}
