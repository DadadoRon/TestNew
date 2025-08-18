package org.example.testnew;

import lombok.RequiredArgsConstructor;
import org.example.testnew.entity.PatientProfile;
import org.example.testnew.importation.models.ClientNoteResponseDto;
import org.example.testnew.importation.service.ClientImportService;
import org.example.testnew.importation.service.ClientNoteImportService;
import org.example.testnew.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportRunner {

    private final ClientImportService clientImportService;
    private final ClientNoteImportService clientNoteImportService;
    private final PatientRepository patientRepository;

    @Scheduled(cron = "0 15 */2 * * *")
    public void runImport() {
        clientImportService.importAndUpdatePatients();
        LocalDate dateFrom = LocalDate.now().minusYears(20);
        LocalDate dateTo = LocalDate.now();
        List<PatientProfile> patients = patientRepository.findAll();
        for (PatientProfile patient : patients) {
            if (patient.getOldClientGuids() == null) {
                continue;
            }
            List<ClientNoteResponseDto> notes = clientNoteImportService.importNotes(
                    "test",
                    patient.getOldClientGuids(),
                    dateFrom,
                    dateTo
            );
        }
    }
}



