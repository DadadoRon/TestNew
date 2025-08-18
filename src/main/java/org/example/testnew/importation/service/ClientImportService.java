package org.example.testnew.importation.service;

import org.example.testnew.importation.models.ClientDto;

import java.util.List;

public interface ClientImportService {

    void importAndUpdatePatients();

    List<ClientDto> importClients();
}
