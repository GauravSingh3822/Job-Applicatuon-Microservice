package com.nexdew.jobms.job.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private Long id;
    private String name;
    private String location;
    private String description;
}