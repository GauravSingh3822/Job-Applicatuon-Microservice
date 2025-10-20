package com.nexdew.jobms.job.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private  Long id;
    private String title;
    private String description;
    private double rating;
}
