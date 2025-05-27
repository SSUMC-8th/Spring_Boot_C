package umc.study.converter;

import umc.study.domain.User;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.requestDTO.UserRequestDTO;
import umc.study.web.dto.responseDTO.UserResponseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserConverter {

    /* 사용자가 회원가입을 진행할 때 결과 result를 만드는 매서드*/
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request){

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
                .name(request.getName())
                .email(request.getEmail())   // 추가된 코드
                .password(request.getPassword())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .age(request.getAge())
                .name(request.getName())
                .role(request.getRole())
                .preferenceFoodList(new ArrayList<>())
                .build();
    }


        public static UserResponseDTO.LoginResultDTO toLoginResultDTO(Long userId, String accessToken) {
            return new UserResponseDTO.LoginResultDTO(userId, accessToken);
        }

}
