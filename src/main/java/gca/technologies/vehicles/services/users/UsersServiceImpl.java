package gca.technologies.vehicles.services.users;

import gca.technologies.vehicles.model.Response;
import gca.technologies.vehicles.model.dto.GCAPaymentDto;
import gca.technologies.vehicles.model.dto.GCAUserPaymentDto;
import gca.technologies.vehicles.model.dto.GCAUsersDto;
import gca.technologies.vehicles.model.dto.ResponseUserPaymentDto;
import gca.technologies.vehicles.model.entity.GCAPaymentsEntity;
import gca.technologies.vehicles.model.entity.GCAUserPaymentEntity;
import gca.technologies.vehicles.model.entity.GCAUsersEntity;
import gca.technologies.vehicles.repository.PaymentsRepository;
import gca.technologies.vehicles.repository.UsersPaymentsRepository;
import gca.technologies.vehicles.repository.UsersRepository;
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
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final PaymentsRepository paymentsRepository;

    private final UsersPaymentsRepository usersPaymentsRepository;

    private ResponseUserPaymentDto buildUserPayments(GCAUsersEntity user) {

        List<GCAPaymentsEntity> payments = new ArrayList<>();

        usersPaymentsRepository.findByUser(user).forEach(userPayment -> {
            payments.add(userPayment.getPaymentType());
        });
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
    public Response getAllUsers() {
        List<GCAUsersEntity> users = usersRepository.findAll();
        if (users.isEmpty()) {
            return GenericServices.buildResponse(Constants.OK_NO_DATA.getResponseCode(), Constants.OK_NO_DATA.getMessage(), null);
        }
        List<ResponseUserPaymentDto> usersPaymentMethods = new ArrayList<>();
        for (GCAUsersEntity user : users) {
            if (usersPaymentMethods.isEmpty()) {
                usersPaymentMethods.add(buildUserPayments(user));
            } else {
                Integer userExists = 0;
                for (ResponseUserPaymentDto userPaymentDto : usersPaymentMethods) {
                    if (Objects.equals(userPaymentDto.getUserId(), user.getRegisterId())) {
                        userExists++;
                    }
                }
                if (Objects.equals(userExists, 0)) {
                    usersPaymentMethods.add(buildUserPayments(user));
                }
            }
        }
        return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), usersPaymentMethods);
    }

    @Override
    public Response getUserById(Integer registerId) {
        try {
            GCAUsersEntity gcaUsersEntity = usersRepository.findById(registerId).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
            return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), buildUserPayments(gcaUsersEntity));
        } catch (BussinesException e) {
            return GenericServices.buildResponse(e.getErrorCode(), e.getMessage(), null);
        }
    }

    @Override
    public Response addUser(GCAUsersDto request) {
        GCAUsersEntity usersEntity = GCAUsersEntity.builder()
                .userName(request.getUserName())
                .name(request.getName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .registerDate(GenericServices.recoverFormattedDate())
                .build();
        usersRepository.save(usersEntity);
        return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), usersEntity);
    }

    @Override
    public Response deleteUserById(Integer registerId) {
        try {
            Response response = getUserById(registerId);
            if (!Objects.equals(response.getResponseCode(), Constants.OK.getResponseCode()))
                return response;
            usersRepository.deleteById(registerId);
            return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), null);
        } catch (Exception e) {
            log.info(e.getMessage());
            return GenericServices.buildResponse(Constants.ERROR.getResponseCode(), Constants.ERROR.getMessage(), null);
        }
    }

    @Override
    public Response addPaymentMethod(GCAUserPaymentDto request) {
        try {
            GCAUsersEntity gcaUserEntity = usersRepository.findById(request.getUserId()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));

            GCAPaymentsEntity payment = paymentsRepository.save(validatePaymentData(request.getPaymentData()));
            GCAUserPaymentEntity gcaUserPayment = usersPaymentsRepository.save(
                    GCAUserPaymentEntity.builder()
                            .user(gcaUserEntity)
                            .paymentType(payment)
                            .build()
            );

            List<GCAPaymentsEntity> gcaPaymentsEntities = new ArrayList<>();
            gcaPaymentsEntities.add(gcaUserPayment.getPaymentType());

            ResponseUserPaymentDto response = ResponseUserPaymentDto.builder()
                    .userId(gcaUserPayment.getUser().getRegisterId())
                    .userName(gcaUserPayment.getUser().getUserName())
                    .name(gcaUserPayment.getUser().getName())
                    .lastName(gcaUserPayment.getUser().getLastName())
                    .password(gcaUserPayment.getUser().getPassword())
                    .registerDate(gcaUserPayment.getUser().getRegisterDate())
                    .paymentType(gcaPaymentsEntities)
                    .build();

            return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), response);
        } catch (BussinesException e) {
            return GenericServices.buildResponse(e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return GenericServices.buildResponse(Constants.ERROR.getResponseCode(), Constants.ERROR.getMessage(), null);
        }
    }

    @Override
    public Response updateUserPaymentMethod(GCAUserPaymentDto request) {

        GCAUsersEntity gcaUserEntity = usersRepository.findById(request.getUserId()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));

        List<GCAUserPaymentEntity> userPaymentList = usersPaymentsRepository.findByUser(gcaUserEntity);
        GCAUserPaymentEntity gcaUserPayment = null;
        for (GCAUserPaymentEntity userPaymentMethod : userPaymentList) {
            if (Objects.equals(userPaymentMethod.getPaymentType().getRegisterId(), request.getPaymentData().getRegisterId())) {
                GCAPaymentsEntity payment = paymentsRepository.save(validatePaymentData(request.getPaymentData()));
                gcaUserPayment = usersPaymentsRepository.findByUserAndPaymentType(gcaUserEntity, payment);

                List<GCAPaymentsEntity> gcaPaymentsEntities = new ArrayList<>();
                gcaPaymentsEntities.add(gcaUserPayment.getPaymentType());

                ResponseUserPaymentDto response = ResponseUserPaymentDto.builder()
                        .userId(gcaUserPayment.getUser().getRegisterId())
                        .userName(gcaUserPayment.getUser().getUserName())
                        .name(gcaUserPayment.getUser().getName())
                        .lastName(gcaUserPayment.getUser().getLastName())
                        .password(gcaUserPayment.getUser().getPassword())
                        .registerDate(gcaUserPayment.getUser().getRegisterDate())
                        .paymentType(gcaPaymentsEntities)
                        .build();
                return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), response);
            }
        }
        throw new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName());
    }

    @Override
    public Response deleteUserPaymentMethod(GCAUserPaymentDto userPayment) {
        GCAUsersEntity gcaUserEntity = usersRepository.findById(userPayment.getUserId()).orElseThrow(() -> new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName()));
        List<GCAUserPaymentEntity> userPaymentList = usersPaymentsRepository.findByUser(gcaUserEntity);
        for (GCAUserPaymentEntity userPaymentMethod : userPaymentList) {
            if (Objects.equals(userPaymentMethod.getPaymentType().getRegisterId(), userPayment.getPaymentData().getRegisterId())) {
                usersPaymentsRepository.deleteById(userPaymentMethod.getRegisterId());
                paymentsRepository.deleteById(userPaymentMethod.getPaymentType().getRegisterId());
                return GenericServices.buildResponse(Constants.OK.getResponseCode(), Constants.OK.getMessage(), null);
            }
        }
        throw new BussinesException(Constants.ERROR_NOT_FOUND.getMessage(), Constants.ERROR_NOT_FOUND.getResponseCode(), this.getClass().getCanonicalName());
    }

    private GCAPaymentsEntity validatePaymentData(GCAPaymentDto paymentDto) {
        String paymentType = paymentDto.getPaymentType().toUpperCase();
        if (Objects.equals(paymentType, "EFECTIVO") || Objects.equals(paymentType, "DEBITO") || Objects.equals(paymentType, "CREDITO")) {
            if (Objects.equals(paymentType, "EFECTIVO")) {
                return GCAPaymentsEntity.builder()
                        .registerId(Objects.nonNull(paymentDto.getRegisterId()) ? paymentDto.getRegisterId() : null)
                        .paymentType(paymentType)
                        .cardNumber(null)
                        .cvv(null)
                        .expirationDate(null)
                        .build();
            } else {
                if (Objects.isNull(paymentDto.getCardNumber()) || Objects.isNull(paymentDto.getCvv()) || Objects.isNull(paymentDto.getExpirationDate()))
                    throw new BussinesException(Constants.ERROR_VALIDATION.getMessage(), Constants.ERROR_VALIDATION.getResponseCode(), this.getClass().getCanonicalName());
                return GCAPaymentsEntity.builder()
                        .registerId(Objects.nonNull(paymentDto.getRegisterId()) ? paymentDto.getRegisterId() : null)
                        .paymentType(paymentType)
                        .cardNumber(paymentDto.getCardNumber())
                        .cvv(paymentDto.getCvv())
                        .expirationDate(paymentDto.getExpirationDate())
                        .build();
            }
        }
        throw new BussinesException(Constants.ERROR.getMessage(), Constants.ERROR.getResponseCode(), this.getClass().getCanonicalName());
    }
}
