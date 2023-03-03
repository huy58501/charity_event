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
public class Purchase implements Serializable {
	
	private static final long serialVersionUID = 991651113;
	private int id;
	private String title = "";
	private double price = 4.95;
	private int delivery = 1;
	
	public Purchase(int id, String title, double price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}
	
	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("price must be 0 or greater, default is 4.95");
		}
		this.price = price;
	}
}
