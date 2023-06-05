package com.spring.aws.spring_aws.dtos;

import lombok.*;

@Getter()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
public class UserResponseDTO {
    Long id;
    private String firstName;
    private String lastName;
    private String email;
}
