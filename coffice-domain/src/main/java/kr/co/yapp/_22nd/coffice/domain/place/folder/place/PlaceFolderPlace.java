package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
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
@EqualsAndHashCode(of = "placeFolderPlaceId")
@EntityListeners(AuditingEntityListener.class)
public class PlaceFolderPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeFolderPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeFolderId")
    private PlaceFolder placeFolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    private Place place;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static PlaceFolderPlace of(
            Member member,
            PlaceFolder placeFolder,
            Place place
    ) {
        PlaceFolderPlace placeFolderPlace = new PlaceFolderPlace();
        placeFolderPlace.member = member;
        placeFolderPlace.placeFolder = placeFolder;
        placeFolderPlace.place = place;
        return placeFolderPlace;
    }
}
