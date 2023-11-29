package com.demo.app.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.app.Model.Admin;
import com.demo.app.Model.Userdata;

public interface webappRepository {

	public List<Admin> getAdminData();
	
	public void insertAdmin(Admin admin);

	void deleteAdmin(int id);

	void uploadCv(Userdata userdata, MultipartFile file) throws IOException;

	List<Userdata> getUserData();

}
