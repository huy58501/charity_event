package ca.sheridancollege.nguye482.a4.beans;

import java.io.Serializable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 991651113;
	private long userId;
	@NonNull
	private String userName;
	@NonNull
	private String encryptedPassword;
	private boolean enabled;
}
