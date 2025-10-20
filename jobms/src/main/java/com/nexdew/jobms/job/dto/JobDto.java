package com.nexdew.jobms.job.dto;

import com.nexdew.jobms.job.external.Company;
import com.nexdew.jobms.job.external.Review;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private Long Id;
    private String title;
    private String description;
    private String location;
    private String minSalary;
    private String maxSalary;
    private Company company;
    private List<Review> review;
}
