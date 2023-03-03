package ca.sheridancollege.nguye482.midterm.beans;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Delivery class.
 * 
 * @author Hoang Chuong Nguyen
 *
 */
@Data
@NoArgsConstructor
public class Delivery implements Serializable {
	
	private static final long serialVersionUID = 991651113;
	private int id;
	private String name = "";
	private double fee = 0.0;
	
	public Delivery(int id, String name,double fee) {
		this.id = id;
		this.name = name;
		this.fee = fee;
	}
}
