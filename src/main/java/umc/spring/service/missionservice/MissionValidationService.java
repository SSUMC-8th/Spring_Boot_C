package umc.spring.service.missionservice;

public interface MissionValidationService {

    boolean isAlreadyAccepted(Long memberId, Long missionId);

    boolean existsMemberAndMission(Long memberId, Long missionId);

}
