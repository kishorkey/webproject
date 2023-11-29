package com.demo.app.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.demo.app.Model.Admin;
import com.demo.app.Model.Userdata;
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
	
	@Value("${insertUserData}")
	private String insertUserDataQuery;
	
	@Value("${getuserData}")
	private String getUserDataQuery;
	
	RowMapper<Admin> adminMapper = (rs, rowNum) -> {
		Admin admin = new Admin();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setAge(rs.getInt("age"));
		admin.setRole(rs.getString("role"));
		return admin;
	};
	
	
	RowMapper<Userdata> usermapper = (rs, rowNum) -> {
		Userdata userdata = new Userdata();
		userdata.setId(rs.getInt("id"));
		userdata.setAdmin_id(rs.getInt("admin_id"));
		userdata.setFilename(rs.getString("filename"));
        userdata.setResume(rs.getBytes("resume"));
		return userdata;
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
	
	@Override
	public void uploadCv(Userdata userdata,MultipartFile file) throws IOException {
		
		try {
		byte[] filrArry = file.getBytes();
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id",1);	
		param.addValue("admin_id",1);
		param.addValue("resume",filrArry);
		param.addValue("filename","kumar.pdf");
		namedJdbcTemplet.query(insertUserDataQuery,param,usermapper);
//		System.out.println(filrArry);
		}
		catch(IOException e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	@Override
	public List<Userdata> getUserData()
	{
		try {
		
			List<Userdata> admin = namedJdbcTemplet.query(getUserDataQuery,usermapper);
			return admin;
		}
		catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
		
	}
	
	

}
