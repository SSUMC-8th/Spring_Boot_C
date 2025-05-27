package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(User user) {
        return MemberResponseDTO.MemberInfoDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender().name())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Long memberId, String accessToken) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

    public static User toUser(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .gender(gender)
                .name(request.getName())
                .role(request.getRole())
                .userFoodList(new ArrayList<>())
                .build();
    }
}
