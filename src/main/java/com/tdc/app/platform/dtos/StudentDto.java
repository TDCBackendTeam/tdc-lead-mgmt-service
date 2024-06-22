package com.tdc.app.platform.dtos;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String studentName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String emailId;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phoneNo;

    private String altPhoneNo;

    private LocalDate birthday;

    @NotBlank(message="fatherName must not be blank")
    @Size(min=3, message="fatherName must be at least 3 characters long")
    private String fatherName;

    @NotBlank(message="motherName must not be blank")
    @Size(min=3, message="motherName must be at least 3 characters long")
    private String motherName;

    @NotBlank(message="course must not be blank")
    @Size(min=3, message="course must be at least 3 characters long")
    private String course;

    private StudentAddressDto address;
}
