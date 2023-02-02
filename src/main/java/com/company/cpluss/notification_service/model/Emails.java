package com.company.cpluss.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emails {

    private String[] to;
    private String subject;
    private String body;
}
