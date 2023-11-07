package gca.technologies.vehicles.repository;

import gca.technologies.vehicles.model.entity.GCAPaymentsEntity;
import gca.technologies.vehicles.model.entity.GCAUserPaymentEntity;
import gca.technologies.vehicles.model.entity.GCAUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersPaymentsRepository extends JpaRepository<GCAUserPaymentEntity, Integer> {

    List<GCAUserPaymentEntity> findByUser(GCAUsersEntity user);

    GCAUserPaymentEntity findByUserAndPaymentType(GCAUsersEntity user, GCAPaymentsEntity paymentType);

    GCAUserPaymentEntity findByPaymentType(GCAPaymentsEntity paymentType);

}
