package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.SupportMessage;

public class SupportMessageRowMapper implements RowMapper<SupportMessage>{

	@Override
	public SupportMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		SupportMessage sm = new SupportMessage();
		sm.setId(rs.getInt("id"));
		sm.setMail(rs.getString("mail"));
		sm.setMessage(rs.getString("message"));
		sm.setName(rs.getString("name"));
		sm.setPhone(rs.getString("phone"));
		sm.setDate(rs.getTimestamp("date"));
		return sm;
	}

}
