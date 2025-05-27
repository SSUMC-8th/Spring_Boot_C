package umc.spring.service.storeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreValidationServiceImpl implements StoreValidationService {

    private final StoreRepository storeRepository;

    @Override
    public boolean existsStore(Long storeId) {
        if (storeId == null) {
            return false;
        }
        return storeRepository.existsById(storeId);
    }

    @Override
    public boolean isActiveStore(Long storeId) {
        if (storeId == null) {
            return false;
        }

        // 가게가 존재하고 활성 상태인지 확인
        return storeRepository.existsById(storeId);
    }
}