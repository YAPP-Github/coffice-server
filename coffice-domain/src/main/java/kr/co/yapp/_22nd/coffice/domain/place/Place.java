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

    @Embedded
    private ElectricOutletCount electricOutletCount;

    @Embedded
    private SeatCount seatCount;

    @Embedded
    private TableCount tableCount;

    @Embedded
    private CommunalTableCount communalTableCount;

    @ElementCollection
    @CollectionTable(name = "place_image_url", joinColumns = @JoinColumn(name = "placeId"))
    private final List<String> imageUrls = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "place_crowdedness", joinColumns = @JoinColumn(name = "placeId"))
    private final List<Crowdedness> crowdednessList = new ArrayList<>();

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
        place.electricOutletCount = placeCreateVo.getElectricOutletCount();
        place.seatCount = placeCreateVo.getSeatCount();
        place.tableCount = placeCreateVo.getTableCount();
        place.communalTableCount = placeCreateVo.getCommunalTableCount();
        place.imageUrls.addAll(placeCreateVo.getImageUrls());
        place.crowdednessList.addAll(placeCreateVo.getCrowdednessList());
        return place;
    }

    public void update(PlaceUpdateVo placeUpdateVo) {
        name = placeUpdateVo.getName();
        coordinates = placeUpdateVo.getCoordinates();
        address = placeUpdateVo.getAddress();
        openingHours.clear();
        openingHours.addAll(placeUpdateVo.getOpeningHours());
        electricOutletCount = placeUpdateVo.getElectricOutletCount();
        seatCount = placeUpdateVo.getSeatCount();
        tableCount = placeUpdateVo.getTableCount();
        communalTableCount = placeUpdateVo.getCommunalTableCount();
        imageUrls.clear();
        imageUrls.addAll(placeUpdateVo.getImageUrls());
        crowdednessList.addAll(placeUpdateVo.getCrowdednessList());
    }

    public void delete() {
        deleted = true;
    }

    public ElectricOutletLevel getElectricOutletLevel() {
        return ElectricOutletLevel.of(electricOutletCount, seatCount);
    }

    public boolean hasCommunalTable() {
        return communalTableCount != null && communalTableCount.isPositive();
    }

    public CapacityLevel getCapacityLevel() {
        return CapacityLevel.from(tableCount);
    }
}
