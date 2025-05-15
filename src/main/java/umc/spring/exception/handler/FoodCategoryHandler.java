package umc.spring.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
