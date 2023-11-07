package gca.technologies.vehicles.services.users;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCAUserPaymentDto;
import gca.technologies.vehicles.model.dto.GCAUsersDto;

public interface UsersService {

    Response getAllUsers();

    Response getUserById(Integer registerId);

    Response addUser(GCAUsersDto request);

    Response deleteUserById(Integer registerId);

    Response addPaymentMethod(GCAUserPaymentDto request);

    Response updateUserPaymentMethod(GCAUserPaymentDto request);

    Response deleteUserPaymentMethod(GCAUserPaymentDto userPayment);

}
