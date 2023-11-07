package gca.technologies.vehicles.repository;

import gca.technologies.vehicles.model.entity.GCAStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<GCAStatusEntity, Integer> {
}
