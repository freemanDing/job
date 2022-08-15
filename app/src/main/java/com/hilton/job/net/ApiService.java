package com.hilton.job.net;

import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/")
    Observable<JobResponse> getJobList(@Body JobRequest request);
}
