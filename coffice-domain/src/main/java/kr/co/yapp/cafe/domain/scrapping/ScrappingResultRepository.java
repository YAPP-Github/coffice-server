package kr.co.yapp.cafe.domain.scrapping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrappingResultRepository extends JpaRepository<ScrappingResult, Long>, ScrappingResultRepositoryCustom {
}
