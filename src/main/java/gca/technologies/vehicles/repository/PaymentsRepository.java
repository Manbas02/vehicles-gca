package gca.technologies.vehicles.repository;

import gca.technologies.vehicles.model.entity.GCAPaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<GCAPaymentsEntity, Integer> {
}
