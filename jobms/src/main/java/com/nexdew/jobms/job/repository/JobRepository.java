package com.nexdew.jobms.job.repository;


import com.nexdew.jobms.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}