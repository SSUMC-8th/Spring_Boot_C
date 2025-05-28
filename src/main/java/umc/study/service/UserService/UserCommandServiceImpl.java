package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.apiPayload.exception.handler.TempHandler;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.User;
import umc.study.domain.mapping.PreferenceFood;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.requestDTO.UserRequestDTO;
import umc.study.web.dto.responseDTO.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {
        User newUser = UserConverter.toUser(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<PreferenceFood> UserPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        UserPreferList.forEach(preferenceFood -> {preferenceFood.setUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));

//        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new TempHandler(ErrorStatus.INVALID_PASSWORD);
//        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return UserConverter.toLoginResultDTO(
                user.getId(),
                accessToken
        );
    }
}
