package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.VerifyUser;

public class VerifyUserRowMapper implements RowMapper<VerifyUser> {

	@Override
	public VerifyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		VerifyUser vu = new VerifyUser();
		vu.setUsername(rs.getString("username"));
		vu.setVerifyCode(rs.getString("verify_code"));
		vu.setId(rs.getInt("id"));
		return vu;
	}

}
