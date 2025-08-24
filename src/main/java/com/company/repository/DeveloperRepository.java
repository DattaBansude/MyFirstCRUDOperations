package com.company.repository;

import com.company.entity.Developer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Integer> {

    @Query("SELECT d FROM Developer d WHERE d.age= :age")
    List<Developer> findByAge(@Param("age") int age);

    @Query(value = "SELECT * FROM Developer",nativeQuery = true)
    List<Developer> findAllDeveloper();

    @Query(value = "SELECT * FROM Developer d where d.developer_id is NULL OR d.developer_id=' '",nativeQuery = true)
    List<Developer> findmissDeveloperId();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Developer SET developer_id=:newdevId where id=:id",nativeQuery = true)
    int updateDeveloperId(int id, String newdevId);
}
