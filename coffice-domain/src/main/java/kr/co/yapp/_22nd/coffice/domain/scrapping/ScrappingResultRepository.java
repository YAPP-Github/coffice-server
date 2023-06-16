package kr.co.yapp._22nd.coffice.domain.scrapping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrappingResultRepository extends JpaRepository<ScrappingResult, Long>, ScrappingResultRepositoryCustom {
}
