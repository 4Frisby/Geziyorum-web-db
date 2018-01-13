package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Admin;
import com.geziyorum.entity.City;

public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setAdminId(rs.getInt("admin_id"));
		admin.setPassword(rs.getString("password"));
		admin.setUsername(rs.getString("username"));
		return admin;
	}

}
