package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>{

    //List<MissionProgressDto> findInProgressMissions(Long memberId, String cursorValue, int limit);

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :status " +
            "ORDER BY mm.createdAt DESC")
    Page<MemberMission> findInProgressMissionsWithDetails(@Param("memberId") Long memberId,
                                                          @Param("status") MissionStatus status,
                                                          Pageable pageable);
}
