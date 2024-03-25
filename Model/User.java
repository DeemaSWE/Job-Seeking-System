package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, message = "Name must be at least 4 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only characters (no numbers)")
    @Column(columnDefinition = "varchar(50) not null check (name REGEXP '^[a-zA-Z ]+$')")
    private String name;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    @Column(columnDefinition = "varchar(100) not null check (email REGEXP '^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$')",
            unique = true)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String password;

    @Min(value = 21, message = "Age must be at least 21")
    @Column(columnDefinition = "int not null check (age>=21)")
    private Integer age;

    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be either JOB_SEEKER or EMPLOYER")
    @Column(columnDefinition = "varchar(10) not null check (role REGEXP '^(JOB_SEEKER|EMPLOYER)$')")
    private String role;

}
