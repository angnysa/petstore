package org.angnysa.petstore.backend.user.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.jpa.entity.UserEntity;
import org.angnysa.petstore.backend.rest.generated.api.UserApi;
import org.angnysa.petstore.backend.rest.generated.model.User;
import org.angnysa.petstore.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserRestController implements UserApi {

    private final UserMapper mapper;
    private final UserService service;

    @Override
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(mapper.mapToRest(service.createUser(mapper.mapToEntity(user))));
    }

    @Override
    public ResponseEntity<User> createUsersWithListInput(List<@Valid User> user) {
        List<UserEntity> users = service.createUsers(user.stream().map(mapper::mapToEntity).toList());

        if (users.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(mapper.mapToRest(users.getFirst()));
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        if (service.deleteUser(username)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<User> getUserByName(String username) {
        return ResponseEntity.of(service.fetchUser(username).map(mapper::mapToRest));
    }

    @Override
    public ResponseEntity<Void> updateUser(String username, User user) {
        if (service.updateUser(username, mapper.mapToEntity(user))) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
