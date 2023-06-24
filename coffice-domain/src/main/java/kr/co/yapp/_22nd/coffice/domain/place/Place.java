package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.*;
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
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String name;

    @Embedded
    private Coordinates coordinates;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "place_opening_hour", joinColumns = @JoinColumn(name = "placeId"))
    private final List<OpeningHour> openingHours = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean deleted = false;

    public static Place from(PlaceCreateVo placeCreateVo) {
        Place place = new Place();
        place.name = placeCreateVo.getName();
        place.coordinates = placeCreateVo.getCoordinates();
        place.address = placeCreateVo.getAddress();
        place.openingHours.addAll(placeCreateVo.getOpeningHours());
        return place;
    }

    public void update(PlaceUpdateVo placeUpdateVo) {
        name = placeUpdateVo.getName();
        coordinates = placeUpdateVo.getCoordinates();
        address = placeUpdateVo.getAddress();
        openingHours.clear();
        openingHours.addAll(placeUpdateVo.getOpeningHours());
    }

    public void delete() {
        deleted = true;
    }
}
