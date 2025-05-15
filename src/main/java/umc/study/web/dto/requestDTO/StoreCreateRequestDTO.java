package umc.study.web.dto.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.enums.StoreCategory;


public class StoreCreateRequestDTO {

    @Getter
    public static class InputStore{

        @NotNull
        String name;
        @NotNull
        String address;
        @NotNull
        StoreCategory storeCategory;
        @NotNull
        Long regionId;


    }
}
