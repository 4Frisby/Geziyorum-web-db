package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.ProfilSikayet;

public class ProfilSikayetRowMapper implements RowMapper<ProfilSikayet> {

	@Override
	public ProfilSikayet mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProfilSikayet profilSikayet = new ProfilSikayet();
		profilSikayet.setDegerlendirildi(rs.getInt("degerlendirildi"));
		profilSikayet.setSikayetciUsername(rs.getString("sikayetci_username"));
		profilSikayet.setSikayetEdilenUsername(rs.getString("sikayet_edilen_username"));
		profilSikayet.setSikayetId(rs.getInt("sikayet_id"));
		profilSikayet.setSikayetMetini(rs.getString("sikayet_metini"));
		profilSikayet.setSikayetType(rs.getInt("sikayet_type"));
		profilSikayet.setSikayetZamani(rs.getTimestamp("profil_sikayet_zamani"));
		profilSikayet.setDegerlendirilmeZamani(rs.getTimestamp("degerlendirilme_zamani"));
		profilSikayet.setKararVerenAdmin(rs.getString("karar_veren_admin"));		
		return profilSikayet;
	}

}
