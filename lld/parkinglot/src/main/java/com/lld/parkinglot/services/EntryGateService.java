package com.lld.parkinglot.services;

import com.lld.parkinglot.dtos.GetTicketDTO;
import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.SpotStatus;
import com.lld.parkinglot.models.Ticket;
import com.lld.parkinglot.models.VehicleType;

public class EntryGateService {

	ParkingSpotService parkingSpotService = new ParkingSpotService();
	SlotAllocationService allocationService = new SlotAllocationService();
	TicketService ticketService = new TicketService();

	public GetTicketDTO createTicket(VehicleType vehicleType) {

		// Early returns
		ParkingSpot parkingSpot = allocationService.allocateSlot(vehicleType);
		if (parkingSpot == null) {
			throw new RuntimeException("Slot not available!");
		}

		// Update parking spot
		parkingSpot.setSpotStatus(SpotStatus.FILLED);
		parkingSpotService.markSlotBooked(parkingSpot);

		// Create and persist ticket
		Ticket ticket = ticketService.createTicket(vehicleType, parkingSpot);

		return GetTicketDTO.builder().entryTime(ticket.getEntryTime()).vehicleType(ticket.getVehicleType()).build();
	}
}
