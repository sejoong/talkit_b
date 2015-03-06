CREATE TABLE member(
	member_id VARCHAR(32) NOT NULL, 
	name VARCHAR(20) NOT NULL,
	password VARCHAR(16) NOT NULL, 
	register_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP,
	CONSTRAINT PRIMARY KEY (member_id)
)