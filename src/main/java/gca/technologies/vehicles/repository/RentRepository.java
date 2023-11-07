package gca.technologies.vehicles.repository;

import gca.technologies.vehicles.model.entity.GCARentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<GCARentEntity, Integer> {
}
