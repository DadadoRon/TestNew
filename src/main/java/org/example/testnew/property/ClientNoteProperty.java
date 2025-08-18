package org.example.testnew.property;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("clients-note")
@Data
@Validated
@Component
public class ClientNoteProperty {
    @NotBlank
    private String url;
}
