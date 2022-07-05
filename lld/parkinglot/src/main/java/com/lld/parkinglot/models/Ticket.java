package com.lld.parkinglot.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ticket extends BaseModel {

	private String ticketId;
	private String vehicleId;
	private VehicleType vehicleType;
	private Integer floorNumber;
	private Integer slotNumber;
	private TicketStatus ticketStatus;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	
}