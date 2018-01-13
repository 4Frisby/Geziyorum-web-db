package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Type;

public class TypeRowMapper implements RowMapper<Type> {

	@Override
	public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
		Type type = new Type();
		type.setDescription(rs.getString("description"));
		type.setTypeCode(rs.getInt("type_code"));
		return type;
	}

}
