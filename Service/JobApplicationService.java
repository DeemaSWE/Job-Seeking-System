package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostService jobPostService;
    private final UserService userService;

    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public void applyForJob(JobApplication jobApplication){
        User user = userService.getUserById(jobApplication.getUser_id());
        jobPostService.getJobPostById(jobApplication.getJobPost_id());

        if(user.getRole().equals("EMPLOYER"))
            throw new IllegalArgumentException("Employers cannot apply for jobs");

        jobApplicationRepository.save(jobApplication);
    }

    public void deleteJobApplication(Integer id){
        JobApplication jobApplication = getJobApplicationById(id);
        jobApplicationRepository.delete(jobApplication);
    }

    public JobApplication getJobApplicationById(Integer id){
        return jobApplicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job Application with id " + id + " not found"));
    }
}
