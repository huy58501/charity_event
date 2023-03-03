CREATE TABLE users (
userid BIGINT PRIMARY KEY AUTO_INCREMENT,
userName VARCHAR(75) NOT NULL UNIQUE,
encryptedpassword VARCHAR(128) NOT NULL,
enabled  BOOLEAN NOT NULL 
);

CREATE TABLE roles (
roleid   BIGINT PRIMARY KEY AUTO_INCREMENT,
rolename VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role (
id     BIGINT PRIMARY KEY AUTO_INCREMENT,
userid BIGINT NOT NULL,
roleid BIGINT NOT NULL,
UNIQUE (userid, roleid),
FOREIGN KEY (userid) REFERENCES users(userid),
FOREIGN KEY (roleid) REFERENCES roles(roleid)
);

CREATE TABLE events (
	id INT PRIMARY KEY AUTO_INCREMENT,
	eventName VARCHAR(255),
	ticketCost DECIMAL(6,2)
);

CREATE TABLE transactions (
	txnId INT PRIMARY KEY AUTO_INCREMENT,
	event INT,
	numTickets INT,
	contact VARCHAR(255),
	FOREIGN KEY (event) REFERENCES events(id)
);
CREATE TABLE userLogs (
	logId bigint primary key auto_increment,
	userName  VARCHAR(75) 
);