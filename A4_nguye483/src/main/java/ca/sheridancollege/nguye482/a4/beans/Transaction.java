package ca.sheridancollege.nguye482.a4.beans;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

	private static final long serialVersionUID = 991651113;
	private int id;
	private int event;
	private int numTickets;
	private String contact;
}
