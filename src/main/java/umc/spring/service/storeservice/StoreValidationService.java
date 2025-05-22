package umc.spring.service.storeservice;

public interface StoreValidationService {

    boolean existsStore(Long storeId);

    boolean isActiveStore(Long storeId);
}