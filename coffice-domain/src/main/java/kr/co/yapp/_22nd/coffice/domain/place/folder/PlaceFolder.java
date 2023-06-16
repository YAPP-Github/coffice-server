package kr.co.yapp._22nd.coffice.domain.place.folder;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = "placeFolderId")
@EntityListeners(AuditingEntityListener.class)
public class PlaceFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeFolderId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private String name;
    @OneToMany(mappedBy = "placeFolder")
    private final List<PlaceFolderPlace> placeFolderPlaces = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

    public static PlaceFolder of(
            Member member,
            PlaceFolderCreateVo placeFolderCreateVo
    ) {
        PlaceFolder placeFolder = new PlaceFolder();
        placeFolder.member = member;
        placeFolder.name = placeFolderCreateVo.getName();
        return placeFolder;
    }

    public void update(PlaceFolderUpdateVo placeFolderUpdateVo) {
        this.name = placeFolderUpdateVo.getName();
    }

    public void delete() {
        this.deleted = true;
    }
}
