package org.example.testnew.importation.models;

import java.time.LocalDateTime;

public record ClientNoteResponseDto(
        String comments,
        String guid,
        LocalDateTime modifiedDateTime,
        String clientGuid,
        LocalDateTime datetime,
        String userLogin,
        LocalDateTime createdDateTime
) { }
