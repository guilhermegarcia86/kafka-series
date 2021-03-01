package com.irs.drainer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Taxpayer {

    private String document;
    private String email;
    private String name;
    private String status;

}
