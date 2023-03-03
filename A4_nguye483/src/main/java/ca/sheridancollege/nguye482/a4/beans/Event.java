package ca.sheridancollege.nguye482.a4.beans;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable{
	private static final long serialVersionUID = 991651113;
	private int id;
	private String eventName;
	private double ticketCost;
	
	public Event(String eventName, double cost) {
		setEventName(eventName);
		setTicketCost(cost);
	}
}
