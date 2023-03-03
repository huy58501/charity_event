package ca.sheridancollege.nguye482.a4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.nguye482.a4.database.DatabaseAccess;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired @Lazy
	private DatabaseAccess da;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ca.sheridancollege.nguye482.a4.beans.User user = da.findUserAccount(username);
		if (user == null) {
			da.addUserLog(username);
			System.out.printf("User not found: %s%n", username);
			throw new UsernameNotFoundException("User"+ username +" not found in database.");
		}
		// get the list of roles from the database
		List <String> roles = da.getRolesById(user.getUserId());
		// convert the list of role strings into a list GrantedAuthority objects
		List <GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for (String role: roles) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		UserDetails userDetails = (UserDetails)(new User(user.getUserName(), 
									user.getEncryptedPassword(), grantList));
		return userDetails;
	}

}
