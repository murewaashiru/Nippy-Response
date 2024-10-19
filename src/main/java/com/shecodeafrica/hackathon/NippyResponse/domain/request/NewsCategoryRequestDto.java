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
public class NewsCategoryRequestDto {
    @Schema(description = "The name of the category", example = "weather")
    @Size(min = 3, max = 100)
    @NotNull(message = "name is required")
    private String name;
}
