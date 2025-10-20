package com.nexdew.jobms.job.clients;

import com.nexdew.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE",
        url = "${company-service.url}")
public interface CompanyClient {
    //1. Define the method signature that matches the external service endpoint
    //2. Use appropriate annotations to map the method to the HTTP request (e.g.,
    // @GetMapping, @PostMapping, etc.)
    //3. Specify the return type and parameters as needed
    // Example:
    // @GetMapping("/companies/{id}")
    // Company getCompanyById(@PathVariable("id") Long id);
    // Add more methods as needed to interact with the external service
    @GetMapping("/company/id/{id}")
    Company getCompany(@PathVariable("id") Long id); // Example method to get a company by ID with Feign
}
