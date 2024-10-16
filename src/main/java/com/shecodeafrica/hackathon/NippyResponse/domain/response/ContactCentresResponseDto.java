package com.shecodeafrica.hackathon.NippyResponse.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ContactCentresResponseDto {
//    private String responseCode;
//    private String responseMessage;
//    private ResponseDto responseData;
//
//    @Data
//    public static class ResponseDto{
        public String name;
        public String address;
        public List<String> phoneNumber;
        public String LGA;
        public String State;
    //}
}