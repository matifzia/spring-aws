package com.spring.aws.spring_aws.models;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity()
@Getter()
@Builder()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
@Table(name="users") 
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
}
