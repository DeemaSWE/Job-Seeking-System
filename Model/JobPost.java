package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 4, message = "Title must be at least 4 characters")
    @Column(columnDefinition = "varchar(50) not null check (length(title)>=4)")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @Positive(message = "Salary must be positive")
    @Column(columnDefinition = "decimal check (salary>=0)")
    private Double salary;

    private LocalDate postingDate;
}
