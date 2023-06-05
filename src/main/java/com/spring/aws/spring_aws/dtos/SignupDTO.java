package com.spring.aws.spring_aws.dtos;

import lombok.*;

@Getter()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
public class SignupDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
