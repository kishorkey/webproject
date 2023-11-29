getAdmin = select * from admin

insertAdmin = insert into admin (name,age,role) values (:name,:age,:role)

deleteAdmin = delete from admin where id = :id

insertUserData = insert into userdata (id,admin_id,resume,filename) values (:id,:admin_id,:resume,:filename)

getuserData = select * from userdata