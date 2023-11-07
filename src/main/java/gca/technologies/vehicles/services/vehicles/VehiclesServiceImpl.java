package gca.technologies.vehicles.services.vehicles;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCARentDto;
import gca.technologies.vehicles.model.dto.ResponseRentDto;
import gca.technologies.vehicles.model.dto.ResponseUserPaymentDto;
import gca.technologies.vehicles.model.entity.*;
import gca.technologies.vehicles.repository.PaymentsRepository;
import gca.technologies.vehicles.repository.RentRepository;
import gca.technologies.vehicles.repository.StatusRepository;
import gca.technologies.vehicles.repository.UsersPaymentsRepository;
import gca.technologies.vehicles.services.users.UsersService;
import gca.technologies.vehicles.util.BussinesException;
import gca.technologies.vehicles.util.Constants;
import gca.technologies.vehicles.util.GenericServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiclesServiceImpl implements VehiclesService {

    private final RentRepository rentRepository;

    private final StatusRepository statusRepository;

    private final UsersPaymentsRepository usersPaymentsRepository;

    private final PaymentsRepository paymentsRepository;

    private final UsersService usersService;

    private ResponseUserPaymentDto buildUserPayments(GCAUsersEntity user, GCAPaymentsEntity payment) {

        List<GCAPaymentsEntity> payments = new ArrayList<>();
        payments.add(payment);

        return ResponseUserPaymentDto.builder()
                .userId(user.getRegisterId())
                .userName(user.getUserName())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .registerDate(user.getRegisterDate())
                .paymentType(payments)
                .build();
    }

    @Override
    public Response rent(GCARentDto request) {
        try {
            GCAStatusEntity status = statusRepository.findById(1).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
            GCAPaymentsEntity paymentsEntity = paymentsRepository.findById(request.getUserPayment()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
            GCAUserPaymentEntity userPayment = usersPaymentsRepository.findByPaymentType(paymentsEntity);


            GCARentEntity gcaRentEntity = GCARentEntity.builder()
                    .value(request.getValue())
                    .vehicle(request.getVehicle())
                    .rentStartDate(GenericServices.recoverFormattedDate())
                    .rentEndDate(null)
                    .userPayment(userPayment)
                    .status(status)
                    .build();
            rentRepository.save(gcaRentEntity);
            ResponseUserPaymentDto responseUserPaymentDto = buildUserPayments(userPayment.getUser(), userPayment.getPaymentType());
            ResponseRentDto responseRentDto = ResponseRentDto.builder()
                    .registerId(gcaRentEntity.getRegisterId())
                    .value(gcaRentEntity.getValue())
                    .vehicle(gcaRentEntity.getVehicle())
                    .rentStartDate(gcaRentEntity.getRentStartDate())
                    .rentEndDate(gcaRentEntity.getRentEndDate())
                    .userPayment(responseUserPaymentDto)
                    .status(gcaRentEntity.getStatus().getStatusType())
                    .build();
            return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), responseRentDto);
        } catch (BussinesException e){
            return GenericServices.buildResponse(e.getErrorCode(), e.getMessage(), null);
        }

    }

    @Override
    public Response listRentals() {
        return GenericServices.buildResponse(200, "OK", rentRepository.findAll());
    }

    @Override
    public Response recoverRent(Integer registerId) {
        GCARentEntity rentEntity = rentRepository.findById(registerId).orElse(null);
        return GenericServices.buildResponse(200, "OK", rentEntity);
    }

    @Override
    public Response updateRentalStatus(GCARentDto request) {
        GCARentEntity rentEntity = rentRepository.findById(request.getRegisterId()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
        GCAStatusEntity status = statusRepository.findById(request.getStatusId()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
        rentEntity.setStatus(status);
        if (Objects.equals(rentEntity.getStatus().getStatusCode(), 2)){
            rentEntity.setRentEndDate(GenericServices.recoverFormattedDate());
        }
        rentRepository.save(rentEntity);

        ResponseUserPaymentDto responseUserPaymentDto = buildUserPayments(rentEntity.getUserPayment().getUser(), rentEntity.getUserPayment().getPaymentType());

        ResponseRentDto responseRentDto = ResponseRentDto.builder()
                .registerId(rentEntity.getRegisterId())
                .value(rentEntity.getValue())
                .vehicle(rentEntity.getVehicle())
                .rentStartDate(rentEntity.getRentStartDate())
                .rentEndDate(rentEntity.getRentEndDate())
                .userPayment(responseUserPaymentDto)
                .status(rentEntity.getStatus().getStatusType())
                .build();

        return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), responseRentDto);
    }
}
