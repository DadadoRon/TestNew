package org.example.testnew.repository;

import org.example.testnew.entity.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientNoteRepository extends JpaRepository<PatientNote, Long> {
    boolean existsByGuid(String guid);

    Optional<PatientNote> findByGuid(String guid);
}
