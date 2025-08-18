package org.example.testnew.importation.models;

public record ClientDto(
        String agency,
        String clientGuid,
        String firstName,
        String lastName,
        String status,
        String dob,
        String createdDateTime
) {}
