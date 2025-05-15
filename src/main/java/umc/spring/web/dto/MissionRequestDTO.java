package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionDTO{
        @NotBlank
        String content;
        @NotNull
        Float point;
        @NotNull
        LocalDate deadline;
    }
}
