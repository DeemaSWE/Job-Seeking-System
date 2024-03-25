package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserService userService;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost, Integer userId){
        User user = userService.getUserById(userId);

        if(user.getRole().equals("JOB_SEEKER"))
            throw new IllegalArgumentException("Job seekers cannot post jobs");

        jobPostRepository.save(jobPost);
    }

    public void updateJobPost(JobPost updatedJobPost, Integer postId, Integer userId){
        User user = userService.getUserById(userId);

        if(user.getRole().equals("JOB_SEEKER"))
            throw new IllegalArgumentException("Job seekers cannot post jobs");

        JobPost jobPost = getJobPostById(postId);

        jobPost.setTitle(updatedJobPost.getTitle());
        jobPost.setDescription(updatedJobPost.getDescription());
        jobPost.setSalary(updatedJobPost.getSalary());
        jobPost.setLocation(updatedJobPost.getLocation());
        jobPost.setPostingDate(updatedJobPost.getPostingDate());

        jobPostRepository.save(jobPost);
    }

    public void deleteJobPost(Integer id, Integer userId){
        User user = userService.getUserById(userId);

        if(user.getRole().equals("JOB_SEEKER"))
            throw new IllegalArgumentException("Job seekers cannot post jobs");

        JobPost jobPost = getJobPostById(id);
        jobPostRepository.delete(jobPost);
    }

    public JobPost getJobPostById(Integer id){
        return jobPostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job post with id " + id + " not found"));
    }
}
