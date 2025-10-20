package com.nexdew.jobms.job.service.impl;


import com.nexdew.jobms.job.clients.CompanyClient;
import com.nexdew.jobms.job.clients.ReviewClient;
import com.nexdew.jobms.job.dto.JobDto;
import com.nexdew.jobms.job.entity.Job;
import com.nexdew.jobms.job.external.Company;
import com.nexdew.jobms.job.external.Review;
import com.nexdew.jobms.job.mapper.JobMapper;
import com.nexdew.jobms.job.repository.JobRepository;
import com.nexdew.jobms.job.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    int attempt = 0;
 //   private List<Job> jobs = new ArrayList<>();
//    private  Long nextId = 1L;

    @Override
//    @CircuitBreaker(name="companyBreaker",
//            fallbackMethod = "companyBreakerFallback")
//    @Retry(name="companyBreaker",
//           fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",
          fallbackMethod = "companyBreakerFallback")
    public List<JobDto> findAllJobs() {
        System.out.println("Retry attempt: " + (++attempt));
        List<Job> jobs = jobRepository.findAll();
        List<JobDto> jobDtos = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Company Service is down");
        return list;
    }

    public JobDto convertToDto(Job job) {
//        JobDto jobDto = new JobDto();
//        jobDto.setJob(job);
//        Company company = restTemplate.getForObject(
//                "http://COMPANY-SERVICE:8081/company/id/" + job.getCompanyId(),
//                Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());



//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {}
//        );
        List<Review> reviews = reviewClient.getReview(job.getCompanyId());

//        List<Review> reviews = reviewResponse.getBody();


        return JobMapper.mapToJobWithCompanyDto(job,company,reviews);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDto findByid(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + id));
        return  convertToDto(job);

    }

    @Override
    public boolean deleteJob(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()) {
//            Job job = iterator.next();
//            if (job.getId().equals(id)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Job existingJob = jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + id));
        if (existingJob.getId().equals(id)) {
                existingJob.setTitle(job.getTitle());
                existingJob.setDescription(job.getDescription());
                existingJob.setLocation(job.getLocation());
                existingJob.setMinSalary(job.getMinSalary());
                existingJob.setMaxSalary(job.getMaxSalary());
                jobRepository.save(existingJob);
                return true;
            }
        return false;
    }


}
