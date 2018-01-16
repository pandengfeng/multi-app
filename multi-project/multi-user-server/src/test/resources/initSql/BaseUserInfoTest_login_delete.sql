delete from base_user_info where account = 'test' and user_name = 'pandengfeng';
insert into base_user_info(
		account,
		user_name,
		password,
		disable,
		operand,
		create_time,
		update_time,
		last_login_time
		)values(
			'test',
			'pandengfeng',
			'123456',
			'0',
			'1',
		 	now(),
			now(),
			now()
		);
