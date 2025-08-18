package org.example.testnew.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient_note")
public class PatientNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
    @Column(name = "created_by_user_id")
    private Long createdByUserId;
    @Column(name = "last_modified_by_user_id")
    private Long lastModifiedByUserId;
    @Column(name = "note")
    private String note;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "guid")
    private String guid;
}
