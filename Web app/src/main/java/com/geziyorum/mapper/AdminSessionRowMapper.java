package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.geziyorum.entity.AdminSession;

public class AdminSessionRowMapper implements RowMapper<AdminSession>{

	@Override
	public AdminSession mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		AdminSession adminSession = new AdminSession();
		adminSession.setCreatedTime(resultSet.getTimestamp("created_time"));
		adminSession.setLastlyActiveTime(resultSet.getTimestamp("lastly_active_time"));
		adminSession.setSessionId(resultSet.getString("id"));
		adminSession.setSessionToken(resultSet.getString("token"));
		adminSession.setAdminId(resultSet.getInt("admin_id"));
		return adminSession;
	}

}
