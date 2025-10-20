package com.nexdew.jobms.job.mapper;

import com.nexdew.jobms.job.dto.JobDto;
import com.nexdew.jobms.job.entity.Job;
import com.nexdew.jobms.job.external.Company;
import com.nexdew.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDto mapToJobWithCompanyDto(Job job, Company company, List<Review> review){
       /*
       Without builder
        JobDto jobWithCompanyDto = new JobDto();
        jobWithCompanyDto.setId(job.getId());
        jobWithCompanyDto.setTitle(job.getTitle());
        jobWithCompanyDto.setDescription(job.getDescription());
        jobWithCompanyDto.setLocation(job.getLocation());
        jobWithCompanyDto.setMinSalary(job.getMinSalary());
        jobWithCompanyDto.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDto.setCompany(company);
        return jobWithCompanyDto;
        */

        // With builder
        return JobDto.builder()
                .Id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .company(company)
                .review(review)
                .build();
    }
}
