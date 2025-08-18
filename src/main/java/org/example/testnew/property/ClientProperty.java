package org.example.testnew.property;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties("clients")
@Data
@Validated
@Component
public class ClientProperty {
        @NotBlank
        private String url;
}
