package com.hostel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Hostel_Tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostel {
	
	@Id
	@NotNull(message = "Hostel Id shouldn't be Null")
	private Integer hostelId;
	@NotNull(message = "HostelName shouldn't be Null")
	private String hostelName;
	@NotNull(message = "WardenName shouldn't be Null")
	@NotBlank(message = "Wardenname should't be blank")
	private String wardenName;
	@Positive
	@Min(value = 1, message = "Minimum no.of rooms in hostel should be One")
	private Integer noOfRooms;
	private String feeDetails;
	@Pattern(regexp = "^\\d{10}$")
	private String mobileNumber;
	@PositiveOrZero
	private Integer occupied;
	@PositiveOrZero
	private Integer vacancy;

}


