package kr.co.yapp._22nd.coffice.domain.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    Page<Place> findByDeletedFalse(Pageable pageable);

    Optional<Place> findByPlaceIdAndDeletedFalse(Long placeId);
}
