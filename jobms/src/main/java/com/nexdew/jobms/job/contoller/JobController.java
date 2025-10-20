package com.nexdew.jobms.job.contoller;

import com.nexdew.jobms.job.dto.JobDto;
import com.nexdew.jobms.job.entity.Job;
import com.nexdew.jobms.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a REST controller
@RequestMapping("/jobs") // Base URL for all job-related endpoints
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments
public class JobController {

    private final JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<JobDto>> findAllJobs() {
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>( "Job created successfully!",HttpStatus.CREATED);

    }

    @GetMapping("/id/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId) {
        JobDto jobDto = jobService.findByid(jobId);
        if(jobDto !=null){
            return new ResponseEntity<>(jobDto,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
        boolean deleted = jobService.deleteJob(jobId);
        if(deleted){
            return new ResponseEntity<>( "Job Deleted Successfully!",HttpStatus.OK);
        }

        return new ResponseEntity<>( "Job Not Found By Id..Please Enter Correct Job Id",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{jobId}")
//    @RequestMapping(name ="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        boolean updatedJob = jobService.updateJob(jobId, job);
        if(updatedJob){
            return new ResponseEntity<>( "Job Updated Successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>( "Not Found By Id..Please Enter Correct Job Id",HttpStatus.NOT_FOUND);
    }
}
