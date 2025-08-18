package org.example.testnew.importation.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientNoteRequestDto(
        String agency,
        LocalDate dateFrom,
        LocalDate dateTo,
        String clientGuid
) { }
