package com.smartfitness.demo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class TimetableAvailable2 {
	
	@NonNull
	private Date firtstAm;
	
	@NonNull
	private Date secondAm;
	
	@NonNull
	private Date firtstPm;
	
	@NonNull
	private Date secondPm;
	
	@NonNull
	private Date thirdAm;
	
	@NonNull
	private Date thirdPm;
	
	@NonNull
	private Date fourthAm;
	
	@NonNull
	private Date fourthPm;
	
}
