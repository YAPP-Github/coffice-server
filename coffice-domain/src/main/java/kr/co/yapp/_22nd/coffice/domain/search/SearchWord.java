package kr.co.yapp._22nd.coffice.domain.search;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = "searchWordId")
@EntityListeners(AuditingEntityListener.class)
public class SearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchWordId;
    private Long memberId;
    private String text;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

    public static SearchWord of(Long memberId, String text) {
        SearchWord searchWord = new SearchWord();
        searchWord.memberId = memberId;
        searchWord.text = text;
        return searchWord;
    }

    public void delete() {
        deleted = true;
    }

}
