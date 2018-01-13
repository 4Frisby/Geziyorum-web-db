package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Session;

public class SessionRowMapper implements RowMapper<Session> {

	@Override
	public Session mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Session session = new Session();
		session.setCreatedTime(resultSet.getTimestamp("created_time"));
		session.setLastlyActiveTime(resultSet.getTimestamp("lastly_active_time"));
		session.setSessionId(resultSet.getString("id"));
		session.setSessionToken(resultSet.getString("token"));
		session.setUserId(resultSet.getInt("user_id"));
		return session;
	}

	
}
