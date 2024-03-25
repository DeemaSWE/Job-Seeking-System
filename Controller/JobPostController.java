package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity addJobPost(@PathVariable Integer userId, @RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        jobPostService.addJobPost(jobPost, userId);
        return ResponseEntity.ok(new ApiResponse("Job Post added successfully"));
    }

    @PutMapping("/update/{postId}/{userId}")
    public ResponseEntity updateJobPost(@PathVariable Integer postId, @PathVariable Integer userId, @RequestBody @Valid JobPost jobPost, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        jobPostService.updateJobPost(jobPost, postId, userId);
        return ResponseEntity.ok(new ApiResponse("Job Post updated successfully"));
    }

    @DeleteMapping("/delete/{postId}/{userId}")
    public ResponseEntity deleteJobPost(@PathVariable Integer postId, @PathVariable Integer userId){
        jobPostService.deleteJobPost(postId, userId);
        return ResponseEntity.ok(new ApiResponse("Job Post deleted successfully"));
    }
}
