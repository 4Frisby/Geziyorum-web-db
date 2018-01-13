package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.ForgotPassword;

public class ForgotPasswordRowMapper implements RowMapper<ForgotPassword>{

	@Override
	public ForgotPassword mapRow(ResultSet rs, int rowNum) throws SQLException {
		ForgotPassword fp = new ForgotPassword();
		fp.setId(rs.getInt("id"));
		fp.setRandomKey(rs.getString("random_key"));
		fp.setUsername(rs.getString("username"));
		return fp;
	}

}
