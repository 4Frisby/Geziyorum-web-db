package com.geziyorum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("typeDao")
public class TypeResolverDaoImpl implements TypeResolverDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getDescription(Integer type) {
		final String sql = "Select description from type where type_code = ?";
		String desc= this.jdbcTemplate.queryForObject(sql, String.class, type);
		return desc;
	}

}
