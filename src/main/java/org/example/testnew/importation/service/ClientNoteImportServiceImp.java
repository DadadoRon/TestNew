package org.example.testnew.importation.service;

import org.example.testnew.entity.PatientNote;
import org.example.testnew.entity.PatientProfile;
import org.example.testnew.importation.models.ClientNoteRequestDto;
import org.example.testnew.importation.models.ClientNoteResponseDto;
import org.example.testnew.property.ClientNoteProperty;
import org.example.testnew.property.ClientProperty;
import org.example.testnew.repository.CompanyUserRepository;
import org.example.testnew.repository.PatientNoteRepository;
import org.example.testnew.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientNoteImportServiceImp implements ClientNoteImportService {
    private final RestClient restClient;
    private final ClientNoteProperty clientNoteProperty;
    private final PatientNoteRepository patientNoteRepository;
    private final CompanyUserService companyUserService;
    private final PatientRepository patientRepository;

    public ClientNoteImportServiceImp(ClientNoteProperty clientNoteProperty, PatientNoteRepository patientNoteRepository,
                                      CompanyUserService companyUserService, PatientRepository patientRepository) {
        this.clientNoteProperty = clientNoteProperty;
        this.patientNoteRepository = patientNoteRepository;
        this.companyUserService = companyUserService;
        this.patientRepository = patientRepository;
        restClient = RestClient.builder()
                .baseUrl(clientNoteProperty.getUrl())
                .build();
    }

    @Override
    public List<ClientNoteResponseDto> importNotes(String agency, String clientGuid, LocalDate dateFrom,
                                                   LocalDate dateTo) {
        ClientNoteRequestDto requestDto = new ClientNoteRequestDto(
                agency,
                dateFrom,
                dateTo,
                clientGuid
        );
        ClientNoteResponseDto[] responseArray = restClient.post()
                .uri("")
                .body(requestDto)
                .retrieve()
                .body(ClientNoteResponseDto[].class);
        List<ClientNoteResponseDto> importedNotes = Arrays.asList(responseArray);
        for (ClientNoteResponseDto noteDto : importedNotes) {
            Optional<PatientNote> existingNoteOpt = patientNoteRepository.findByGuid(noteDto.guid());
            if (existingNoteOpt.isPresent()) {
                PatientNote existingNote = existingNoteOpt.get();
                if (noteDto.modifiedDateTime().isAfter(existingNote.getLastModifiedDateTime())) {
                    existingNote.setNote(noteDto.comments());
                    existingNote.setCreatedDateTime(noteDto.createdDateTime());
                    existingNote.setLastModifiedDateTime(noteDto.modifiedDateTime());
                    Long userId = companyUserService.findOrCreateUserIdByLogin(noteDto.userLogin());
                    existingNote.setCreatedByUserId(userId);
                    existingNote.setLastModifiedByUserId(userId);
                    patientNoteRepository.save(existingNote);
                }
                continue;
            }
            PatientNote newNote = new PatientNote();
            newNote.setNote(noteDto.comments());
            newNote.setCreatedDateTime(noteDto.createdDateTime());
            newNote.setLastModifiedDateTime(noteDto.modifiedDateTime());
            Long userId = companyUserService.findOrCreateUserIdByLogin(noteDto.userLogin());
            newNote.setCreatedByUserId(userId);
            newNote.setLastModifiedByUserId(userId);
            PatientProfile patient = patientRepository.findByOldClientGuid(noteDto.clientGuid());
            newNote.setPatientId(patient.getId());
            newNote.setGuid(noteDto.guid());
            patientNoteRepository.save(newNote);
        }
        return importedNotes;
    }
}
