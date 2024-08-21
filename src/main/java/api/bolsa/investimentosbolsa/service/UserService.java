package api.bolsa.investimentosbolsa.service;

import api.bolsa.investimentosbolsa.controller.dto.AccountResponseDto;
import api.bolsa.investimentosbolsa.controller.dto.CreateAccountDto;
import api.bolsa.investimentosbolsa.controller.dto.CreateUserDto;
import api.bolsa.investimentosbolsa.controller.dto.UpdateUserDto;
import api.bolsa.investimentosbolsa.entity.Account;
import api.bolsa.investimentosbolsa.entity.BillingAddress;
import api.bolsa.investimentosbolsa.entity.User;
import api.bolsa.investimentosbolsa.repository.AccountRepository;
import api.bolsa.investimentosbolsa.repository.BillingAddressRepository;
import api.bolsa.investimentosbolsa.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }


    public UUID createUser(CreateUserDto createUserDto) {

        // converter DTO para ENTITY
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(), null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY

        var account = new Account(
                UUID.randomUUID(),
                user.getUserId(),
                null,
                createAccountDto.description(),
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
            accountCreated.getAccountId(),
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(ac ->
                        new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
