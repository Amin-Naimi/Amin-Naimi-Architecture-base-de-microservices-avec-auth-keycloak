package com.Mohamed.front_service.models;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private Long id;
    private Date billingDate;
    private Long customerId;

}
