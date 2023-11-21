package com.demo.app.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.app.Model.Admin;
import com.demo.app.configuration.postgresConnection;

@Repository
@PropertySource(value = {"classpath:/queries.sql"})
public class webappRepositoryImp implements webappRepository {
	
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplet;
	
	@Autowired
	postgresConnection postgresconn;
	
	@Value("${getAdmin}")
	private String getAdminQuery;
	
	@Value("${insertAdmin}")
	private String insertAdminQuery;
	
	@Value("${deleteAdmin}")
	private String deleteAdminQuery;
	
	RowMapper<Admin> adminMapper = (rs, rowNum) -> {
		Admin admin = new Admin();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setAge(rs.getInt("age"));
		admin.setRole(rs.getString("role"));
		return admin;
	};
	
	@Override
	public List<Admin> getAdminData()
	{
		try {
		
			List<Admin> admin = namedJdbcTemplet.query(getAdminQuery,adminMapper);
			return admin;
		}
		catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
		
	}
	
	@Override
	public void insertAdmin(Admin admin)	{
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("name",admin.getName());
			param.addValue("age",admin.getAge());
			param.addValue("role",admin.getRole());
			
			namedJdbcTemplet.query(insertAdminQuery,param,adminMapper);
//			return admin;
		}
		catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	
	@Override
	public void deleteAdmin(int id)	{
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id",id);		
			namedJdbcTemplet.query(deleteAdminQuery,param,adminMapper);
//			return admin;
		}
		catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	

}
