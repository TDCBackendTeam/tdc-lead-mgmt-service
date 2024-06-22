package com.tdc.app.platform.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String studentName;

    private String emailId;

    private String phoneNo;

    private String altPhoneNo;

    private LocalDate birthday;

    private String fatherName;

    private String motherName;

    private String courseName;
    
    private Integer courseId;

    private StudentAddressResponse address;
}
