getAdmin = select * from admin

insertAdmin = insert into admin (name,age,role) values (:name,:age,:role)

deleteAdmin = delete from admin where id = :id