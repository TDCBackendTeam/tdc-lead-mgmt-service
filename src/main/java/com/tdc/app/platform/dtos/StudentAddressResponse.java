package com.tdc.app.platform.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddressResponse {
	
	private Integer addId;

    private String street1;

    private String street2;

    private String city;

    private String state;

    private String pincode;

    private String country;

}
