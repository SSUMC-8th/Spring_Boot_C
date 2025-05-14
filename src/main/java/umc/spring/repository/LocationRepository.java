package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Location;

public interface LocationRepository extends JpaRepository <Location, Long>{
}
