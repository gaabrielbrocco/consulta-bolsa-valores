package api.bolsa.investimentosbolsa.service;

import api.bolsa.investimentosbolsa.controller.CreateUserDto;
import api.bolsa.investimentosbolsa.entity.User;
import api.bolsa.investimentosbolsa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
