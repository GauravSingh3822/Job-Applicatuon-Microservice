package com.nexdew.jobms.job.service;




import com.nexdew.jobms.job.dto.JobDto;
import com.nexdew.jobms.job.entity.Job;

import java.util.List;


public interface JobService {

    List<JobDto> findAllJobs();
    void createJob(Job job);


    JobDto findByid(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id, Job job);
}
