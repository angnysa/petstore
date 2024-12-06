package org.angnysa.petstore.backend.user.service;

import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.jpa.entity.UserEntity;
import org.angnysa.petstore.backend.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> createUsers(List<UserEntity> users) {
        return userRepository.saveAll(users);
    }

    public boolean deleteUser(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<UserEntity> fetchUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean updateUser(String username, UserEntity changes) {
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            user.setUsername(changes.getUsername());
            user.setFirstName(changes.getFirstName());
            user.setLastName(changes.getLastName());
            user.setEmail(changes.getEmail());
            user.setPassword(changes.getPassword());
            user.setPhone(changes.getPhone());
            user.setStatus(changes.getStatus());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
