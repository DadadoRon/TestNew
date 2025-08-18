package org.example.testnew.importation.service;


import org.example.testnew.repository.PatientRepository;
import org.example.testnew.entity.PatientProfile;
import org.example.testnew.entity.Status;
import org.example.testnew.importation.models.ClientDto;
import org.example.testnew.property.ClientProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientImportServiceImp implements ClientImportService {
    private final ClientProperty clientProperty;
    private final RestClient restClient;
    private final PatientRepository patientRepository;

    public ClientImportServiceImp(ClientProperty clientProperty, PatientRepository patientRepository) {
        this.clientProperty = clientProperty;
        this.patientRepository = patientRepository;
        restClient = RestClient.builder()
                .baseUrl(clientProperty.getUrl())
                .build();
    }

    @Override
    public List<ClientDto> importClients() {
        ClientDto[] clientsArray = restClient.post()
                .uri("")
                .retrieve()
                .body(ClientDto[].class);


        return Arrays.stream(clientsArray)
                .filter(client -> "ACTIVE".equalsIgnoreCase(client.status()))
                .collect(Collectors.toList());
    }

    @Override
    public void importAndUpdatePatients() {
        List<ClientDto> clients = importClients();
        List<PatientProfile> allPatients = patientRepository.findAll();
        for (ClientDto client : clients) {
        }
        for (ClientDto client : clients) {
            boolean foundByGuid = false;
            for (PatientProfile patient : allPatients) {
                if (patient.getOldClientGuids() == null){
                    break;
                }
                if (patient.getOldClientGuids().contains(client.clientGuid())) {
                    foundByGuid = true;
                    break;
                }
            }
            if (foundByGuid) {
                continue;
            }
            boolean updated = false;
            for (PatientProfile patient : allPatients) {
                if (patient.getFirstName().equalsIgnoreCase(client.firstName()) &&
                        patient.getLastName().equalsIgnoreCase(client.lastName())) {
                    String newGuids = patient.getOldClientGuids() + "," + client.clientGuid();
                    patient.setOldClientGuids(newGuids);
                    patientRepository.save(patient);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                PatientProfile newPatient = new PatientProfile();
                newPatient.setFirstName(client.firstName());
                newPatient.setLastName(client.lastName());
                newPatient.setOldClientGuids(client.clientGuid());
                newPatient.setStatusId((short) Status.ACTIVE.getCode());
                patientRepository.save(newPatient);
                allPatients.add(newPatient);
            }
        }
    }
}
