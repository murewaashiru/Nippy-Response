package com.shecodeafrica.hackathon.NippyResponse.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestDto {
    @Schema(description = "Requester's name", example = "John Doe")
    @Size(min = 3, max = 30)
    //@NotNull(message = "name is required")
    private String name;

    @Size(min = 3, max = 30)
    private String emergencyType;

    @Schema(description = "Recipient Number", example = "08000000000")
    @Size(min = 3, max = 14)
    //@NotNull(message = "name is required")
    private String recipientNumber;

    @Schema(description = "SMS to be sent", example = "This is an SOS message")
    @Size(min = 3, max = 30)
    @NotNull(message = "message is required")
    private String message;
}
