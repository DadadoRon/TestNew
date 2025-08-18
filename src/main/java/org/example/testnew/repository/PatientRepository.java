package org.example.testnew.repository;

import org.example.testnew.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientProfile, Long> {

    List<PatientProfile> findAll();

    @Query("SELECT p FROM PatientProfile p WHERE p.oldClientGuids LIKE %:guid%")
    PatientProfile findByOldClientGuid(@Param("guid") String guid);

}
