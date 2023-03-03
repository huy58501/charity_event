package ca.sheridancollege.nguye482.a4.beans;

import java.io.Serializable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLog implements Serializable{
	private static final long serialVersionUID = 991651113;
	private long logId;
	private String userName;
}