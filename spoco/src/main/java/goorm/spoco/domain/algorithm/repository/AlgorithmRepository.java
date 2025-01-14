package goorm.spoco.domain.algorithm.repository;

import goorm.spoco.domain.algorithm.domain.Algorithm;
import goorm.spoco.domain.algorithm.domain.AlgorithmStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {

    Optional<Algorithm> findAlgorithmByAlgorithmIdAndAlgorithmStatus(Long id, AlgorithmStatus status);

    //== 알고리즘 리스트를 반환 후 그 중에서 클릭? 으로 검색하는 방식 + 오름차순으로 정렬==//
    List<Algorithm> findAlgorithmsByTitleLikeAndAlgorithmStatusOrderByTitleAsc(String title, AlgorithmStatus algorithmStatus);

    //== 제목% 으로 검색 ==//
    @Query(value = "SELECT * FROM Algorithm a WHERE a.title LIKE CONCAT('%-', ?1) AND a.algorithm_status = ?2 ORDER BY a.title", nativeQuery = true)
    List<Algorithm> findAlgorithmsByOnlyTitle(String title, String status);

    //== 번호% or %제목% 검색 , 추후에  ==//
    List<Algorithm> findAlgorithmsByTitleLikeAndAlgorithmStatus(String Title, AlgorithmStatus status);

}