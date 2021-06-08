package com.example.kidszonea4arctic3.repositories;

import com.example.kidszonea4arctic3.models.Child;
import com.example.kidszonea4arctic3.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long>{

    @Transactional
    @Modifying
    @Query("update Child c set c.fName=:fName where c.id=:id ")
    void updateChildFName(Long id, String fName);

    @Transactional
    @Modifying
    @Query("update Child c set c.age=:age where c.id=:id ")
    void updateChildAge(Long id, int age);

    @Transactional
    @Modifying
    @Query("update Child c set c.gender=:gender where c.id=:id ")
    void updateChildGender(Long id, String gender);

    @Query("SELECT c FROM Child c where c.parent=:parent")
    public List<Child> getMyChildren(Parent parent);



}
