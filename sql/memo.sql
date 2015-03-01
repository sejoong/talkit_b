CREATE TABLE memo(
	memo_id VARCHAR(32) NOT NULL,
	user_id VARCHAR(32) NOT NULL,
	title VARCHAR(30),
	content VARCHAR(500),
	attahement VARCHAR(100),
	attahement_link VARCHAR(200),
	thmubnail VARCHAR(100),
	position_x VARCHAR(10) NOT NULL,
	position_y VARCHAR(10) NOT NULL,
	register_date TIMESTAMP NOT NULL,
	update_date TIMESTAMP,
	CONSTRAINT PRIMARY KEY (memo_id, user_id),
	CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id)
)