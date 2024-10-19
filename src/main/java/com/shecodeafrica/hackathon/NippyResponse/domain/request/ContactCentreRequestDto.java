package com.shecodeafrica.hackathon.NippyResponse.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ContactCentreRequestDto {
        @Schema(description = "name", example = "VI Police")
        @Size(min = 3, max = 100)
        @NotNull(message = "name is required")
        public String name;

        @Schema(description = "category", example = "police")
        @Size(min = 3, max = 100)
        @NotNull(message = "category is required")
        public String category;

        @Schema(description = "Address", example = "VI Police, VI")
        @Size(min = 3, max = 100)
        public String address;

        @Schema(description = "Phone Number", example = "08000000000,08000000001")
        @Size(min = 3, max = 100)
        public String phoneNumber;

        @Schema(description = "Local Government Area", example = "Eti-Osa")
        @Size(min = 3, max = 100)
        public String lga;

        @Schema(description = "State", example = "Lagos")
        @Size(min = 3, max = 100)
        public String state;
}