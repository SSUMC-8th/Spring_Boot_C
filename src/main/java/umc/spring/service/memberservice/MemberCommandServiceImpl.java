package umc.spring.service.memberservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.dto.web.MemberRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        // Converter에서 모든 변환 처리
        Member newMember = MemberConverter.toMemberDTO(request);

        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        // 저장 및 반환
        return memberRepository.save(newMember);
    }
}
