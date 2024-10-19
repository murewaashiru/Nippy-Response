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
public class NewsRequestDto {
    @Schema(description = "Category", example = "weather")
    @Size(min = 3, max = 100)
    @NotNull(message = "category is required")
    private String category;

    @Schema(description = "The details of the news/alert itself", example = "It will rain heavily tomorrow")
    @Size(min = 3, max = 4000)
    @NotNull(message = "message is required")
    private String message;
}
