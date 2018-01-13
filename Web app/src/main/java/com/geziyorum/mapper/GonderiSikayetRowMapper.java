package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.GonderiSikayet;

public class GonderiSikayetRowMapper implements RowMapper<GonderiSikayet> {

	@Override
	public GonderiSikayet mapRow(ResultSet rs, int rowNum) throws SQLException {
		GonderiSikayet gonderiSikayet = new GonderiSikayet();
		gonderiSikayet.setDegerlendirildi(rs.getInt("degerlendirildi"));
		gonderiSikayet.setId(rs.getInt("sikayet_id"));
		gonderiSikayet.setSikayetciUsername(rs.getString("sikayetci_username"));
		gonderiSikayet.setSikayetEdilenGonderiId(rs.getInt("sikayet_edilen_gonderi_id"));
		gonderiSikayet.setSikayetEdilenUsername(rs.getString("sikayet_edilen_username"));
		gonderiSikayet.setSikayetMetini(rs.getString("sikayet_metini"));
		gonderiSikayet.setSikayetType(rs.getInt("sikayet_type"));
		gonderiSikayet.setSikayetZamani(rs.getTimestamp("gonderi_sikayet_zamani"));
		gonderiSikayet.setDegerlendirilmeZamani(rs.getTimestamp("degerlendirilme_zamani"));
		gonderiSikayet.setKararVerenAdmin(rs.getString("karar_veren_admin"));
		return gonderiSikayet;
	}

	
}
