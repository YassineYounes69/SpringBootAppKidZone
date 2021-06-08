package com.example.kidszonea4arctic3.repositories;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Employee;
import com.example.kidszonea4arctic3.models.Parent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e where e.email=:email and e.pw=:password and e.role='Director'")
    public Employee getEmployeeByLoginPw(@Param("email") String login, @Param("password") String password);

    @Query(value = "SELECT e FROM Employee e where e.ccc=:ccc and e.role <> 'Director'")
    public List<Employee> getEmployeeListByCcc(@Param("ccc") ChildCareCenter ccc);

    @Transactional
    @Modifying
    @Query("update Employee e set e.ccc=:ccc where e.id=:id ")
    public void updateEmployeeCcc(@Param("id")Long id,@Param("ccc") ChildCareCenter ccc);

    @Transactional
    @Modifying
    @Query("update Employee e set e.lName=:lname , e.fName=:fname where e.id=:id ")
    public void updateEmployee(@Param("id")Long id,@Param("lname") String lname,@Param("fname") String fname );

    @Transactional
    @Modifying
    @Query("delete from Employee e  where e.id=:id ")
    public void deleteEmployeeById(@Param("id")Long id);

}
