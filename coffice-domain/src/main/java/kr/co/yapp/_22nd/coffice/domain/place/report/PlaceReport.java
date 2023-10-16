package kr.co.yapp._22nd.coffice.domain.place.report;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.*;
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
@EqualsAndHashCode(of = "placeReportId")
@EntityListeners(AuditingEntityListener.class)
public class PlaceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeReportId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private Long placeId = null;
    private Boolean isRegistered = false;
    @Embedded
    Coordinates coordinates;
    private String name;
    @Embedded
    private Address address;
    @Embedded
    private PhoneNumber phoneNumber;
    private ElectricOutletLevel electricOutletLevel;
    private CapacityLevel capacityLevel;
    private Boolean hasCommunalTable;

    @ElementCollection
    @CollectionTable(name = "placeReport_image_url", joinColumns = @JoinColumn(name = "placeReportId"))
    private final List<String> imageUrls = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "placeReport_food_type", joinColumns = @JoinColumn(name = "placeReportId"))
    private final List<FoodType> foodTypes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "placeReport_restroom_type", joinColumns = @JoinColumn(name = "placeReportId"))
    private final List<RestroomType> restroomTypes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "placeReport_drink_type", joinColumns = @JoinColumn(name = "placeReportId"))
    private final List<DrinkType> drinkTypes = new ArrayList<>();

    private String text;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

    public static PlaceReport of(
            Member member,
            PlaceReportCreateVo placeReportCreateVo
    ) {
        PlaceReport placeReport = new PlaceReport();
        placeReport.member = member;
        placeReport.coordinates = placeReportCreateVo.getCoordinates();
        placeReport.name = placeReportCreateVo.getName();
        placeReport.address = placeReportCreateVo.getAddress();
        placeReport.phoneNumber = placeReportCreateVo.getPhoneNumber();
        placeReport.electricOutletLevel = placeReportCreateVo.getElectricOutletLevel();
        placeReport.capacityLevel = placeReportCreateVo.getCapacityLevel();
        placeReport.hasCommunalTable = placeReportCreateVo.getHasCommunalTable();
        placeReport.imageUrls.addAll(placeReportCreateVo.getImageUrls());
        placeReport.foodTypes.addAll(placeReportCreateVo.getFoodTypes());
        placeReport.restroomTypes.addAll(placeReportCreateVo.getRestroomTypes());
        placeReport.drinkTypes.addAll(placeReportCreateVo.getDrinkTypes());
        placeReport.text = placeReportCreateVo.getText();
        return placeReport;
    }

    public void delete() {
        deleted = true;
    }

    public void register(Long placeId) {
        this.placeId = placeId;
        this.isRegistered = true;
    }
}

