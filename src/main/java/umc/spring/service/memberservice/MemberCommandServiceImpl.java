package umc.spring.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.enums.FoodCategory;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        // Converter에서 모든 변환 처리
        Member newMember = MemberConverter.toMemberDTO(request);

        // 저장 및 반환
        return memberRepository.save(newMember);
    }
}
