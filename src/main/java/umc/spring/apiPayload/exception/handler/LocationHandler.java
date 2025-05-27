package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class LocationHandler extends GeneralException {
    public LocationHandler(BaseErrorCode code) {
        super(code);
    }
}
