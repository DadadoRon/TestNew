package org.example.testnew.importation.service;

import org.example.testnew.importation.models.ClientNoteResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ClientNoteImportService {

    List<ClientNoteResponseDto> importNotes(String agency, String clientGuid, LocalDate dateFrom,
                                                   LocalDate dateTo);
}
