package umc.spring.dto.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDTO {
        @NotBlank
        String name;

        @NotBlank
        @Email
        String email;

        @NotBlank
        String password;

        @NotNull
        Integer gender;

        @NotNull
        LocalDate birthDate;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)
        String specAddress;

        @ExistCategories
        List<String> preferCategory;

        @NotNull
        Role role;
    }
}
