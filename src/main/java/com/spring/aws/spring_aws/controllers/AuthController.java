package com.spring.aws.spring_aws.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.spring.aws.spring_aws.dtos.*;
import com.spring.aws.spring_aws.models.User;
import com.spring.aws.spring_aws.repositories.AuthRepository;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signUpRequest) throws Exception {
        if (authRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already taken!");
        }
        User user = convertToUserEntity(signUpRequest);
        authRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() throws Exception {
        List<User> users = authRepository.findAll();
        return users.stream()
                .map(this::convertToUserResponseDto)
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{id}")
    public UserResponseDTO updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        User user = authRepository.findById(id)
                .orElseThrow();
        if (updateUserDTO.getEmail() != null) {
            user.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getFirstName() != null) {
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            user.setLastName(updateUserDTO.getLastName());
        }
        return convertToUserResponseDto(authRepository.save(user));
    }

    private UserResponseDTO convertToUserResponseDto(User user) {
        UserResponseDTO userResponseDto = modelMapper.map(user, UserResponseDTO.class);
        return userResponseDto;
    }

    private User convertToUserEntity(SignupDTO signupDto) throws ParseException {
        User user = modelMapper.map(signupDto, User.class);
        return user;
    }
}
