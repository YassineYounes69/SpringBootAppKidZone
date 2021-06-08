package com.example.kidszonea4arctic3.repositories;

import com.example.kidszonea4arctic3.models.ChildCareCenter;
import com.example.kidszonea4arctic3.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChildCareCenterRepository extends CrudRepository<ChildCareCenter, Long>, JpaRepository<ChildCareCenter, Long>, JpaSpecificationExecutor<ChildCareCenter> {
    @Query("SELECT c FROM ChildCareCenter c where c.id=:id ")
    public ChildCareCenter getChildCareCenterById(@Param("id") Long id);
}
