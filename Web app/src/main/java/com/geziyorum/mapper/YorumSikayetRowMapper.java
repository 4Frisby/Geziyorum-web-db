package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.YorumSikayet;

public class YorumSikayetRowMapper implements RowMapper<YorumSikayet> {

	@Override
	public YorumSikayet mapRow(ResultSet rs, int rowNum) throws SQLException {
		YorumSikayet yorumSikayet = new YorumSikayet();
		yorumSikayet.setDegerlendirildi(rs.getInt("degerlendirildi"));
		yorumSikayet.setSikayetciUsername(rs.getString("sikayetci_username"));
		yorumSikayet.setSikayetEdilenUsername(rs.getString("sikayet_edilen_username"));
		yorumSikayet.setSikayetEdilenYorumId(rs.getInt("sikayet_edilen_yorum_id"));
		yorumSikayet.setSikayetId(rs.getInt("sikayet_id"));
		yorumSikayet.setSikayetMetini(rs.getString("sikayet_metini"));
		yorumSikayet.setSikayetType(rs.getInt("sikayet_type"));
		yorumSikayet.setSikayetZamani(rs.getTimestamp("gonderi_sikayet_zamani"));
		yorumSikayet.setYorumIcerik(rs.getString("yorum_icerik"));
		yorumSikayet.setDegerlendirilmeZamani(rs.getTimestamp("degerlendirilme_zamani"));
		yorumSikayet.setKararVerenAdmin(rs.getString("karar_veren_admin"));			
		return yorumSikayet;
	}

}
