package gca.technologies.vehicles.repository;

import gca.technologies.vehicles.model.entity.GCAUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<GCAUsersEntity, Integer> {
}
