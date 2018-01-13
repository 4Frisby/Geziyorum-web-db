package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.AramaSession;

public class AramaSessionRowMapper implements RowMapper<AramaSession>{

	@Override
	public AramaSession mapRow(ResultSet rs, int rowNum) throws SQLException {
		AramaSession aramaSession = new AramaSession();
		aramaSession.setAramaMetni(rs.getString("arama_metini"));
		aramaSession.setArananTip(rs.getInt("aranan_tip"));
		aramaSession.setToken(rs.getString("token"));
		return aramaSession;
	}

}
