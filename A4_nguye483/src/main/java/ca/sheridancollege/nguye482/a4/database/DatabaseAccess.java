package ca.sheridancollege.nguye482.a4.database;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.nguye482.a4.beans.Event;
import ca.sheridancollege.nguye482.a4.beans.Transaction;
import ca.sheridancollege.nguye482.a4.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	
	public User findUserAccount(String userName) {
		String sql = "Select * from users where userName=:userName;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", userName);
		List<User> results = jdbc.query(sql, params, new BeanPropertyRowMapper<User>(User.class));
		if (results.size() > 0)
				return results.get(0);
		else 
			return null;
	}
	
	public List<String> getRolesById(long userId){
		String sql = "select user_role.userid, roles.rolename"
				+	" from user_role, roles where user_role.roleid = roles.roleid"
				+ 	" and user_role.userid=:userid;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userid", userId);
		List<String> roles = new ArrayList<String>();
		List<Map<String,Object>> rows = jdbc.queryForList(sql,params);
		for (Map<String,Object>row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
	public List<Event> getEvent(){
		String sql = "select * from events order by eventName";
		
		List<Event> event = jdbc.query(sql, 
	            new BeanPropertyRowMapper<Event>(Event.class));
	    return event;
	}
	
	public int addTransaction(Transaction trans) {
		String sql = "insert into transactions(event,numTickets,contact)" + 
					 "values(:event,:numTickets,:contact)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("event", trans.getEvent());
		params.addValue("numTickets", trans.getNumTickets());
		params.addValue("contact", trans.getContact());
		return jdbc.update(sql, params);
	}
	

	public List<Transaction> getTrans(){
		String sql = "select * from transactions";
		List<Transaction> trans = jdbc.query(sql, 
	            new BeanPropertyRowMapper<Transaction>(Transaction.class));
	    return trans;
	}
	
	public int addUserLog(String userName) {
		String sql = "insert into userLogs(userName) values (:userName)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", userName);
		return  jdbc.update(sql, params);
	}
}
