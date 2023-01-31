package com.xeTraining.wizardInfoservice.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String type;
    private int code;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timeStamp;
    private String traceID;
    private Map<String, Object> errors;

    public Map<String, Object> errorResponse() {
        Map<String, Object> status = new HashMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("type", getType());
        details.put("code", getCode());
        details.put("timestamp", getTimeStamp());
        details.put("traceId", getTraceID());
        details.put("errors", getErrors());
        details.put("status", details);
        return details;
    }
}
