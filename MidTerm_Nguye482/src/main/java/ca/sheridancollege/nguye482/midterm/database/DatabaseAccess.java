package ca.sheridancollege.nguye482.midterm.database;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.nguye482.midterm.beans.Delivery;
import ca.sheridancollege.nguye482.midterm.beans.Purchase;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	// output data from database delivery
	public List<Delivery> getDelivery(){
		// sql query to collect all data from delivery table
		String sql = "select * from delivery order by name";
		
		List<Delivery> delivery = jdbc.query(sql, 
	            new BeanPropertyRowMapper<Delivery>(Delivery.class));
	    return delivery;
	}
	
	// output data from database purchase
		public List<Purchase> getPurchase(){
			// sql query to collect all data from purchase table
			String sql = "select * from purchases order by id";
			
			List<Purchase> purchase = jdbc.query(sql, 
		            new BeanPropertyRowMapper<Purchase>(Purchase.class));
		    return purchase;
		}
	
	// add data from user input to Purchase database via Purchase object
	public int addPurchase(Purchase purchase) {
		// sql query to add value to table
		String sql = "insert into purchases(id,title,price,delivery)" + 
					 "values(:id,:title,:price,:delivery)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		// add value to params relate to object variable
		params.addValue("id", purchase.getId());
		params.addValue("title", purchase.getTitle());
		params.addValue("price", purchase.getPrice());
		params.addValue("delivery", purchase.getDelivery());
		return jdbc.update(sql, params);
	}
}
