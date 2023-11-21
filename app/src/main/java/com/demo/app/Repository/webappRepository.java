package com.demo.app.Repository;

import java.util.List;

import com.demo.app.Model.Admin;

public interface webappRepository {

	public List<Admin> getAdminData();
	
	public void insertAdmin(Admin admin);

	void deleteAdmin(int id);

}
