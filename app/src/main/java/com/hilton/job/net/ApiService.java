package com.hilton.job.net;


import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 请求数据接口
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public interface ApiService {

    /**
     * 广告banner
     *
     * @return
     */
    @POST(".")
    Observable<JobResponse> getJobs(@Body JobRequest request);
    /**
     * 搜索图书
     * @param q
     * @param tag
     * @param start
     * @param end
     * @return
     */
//    @GET("book/search")
//    Observable<BookModel> getBooks(@Query("q") String q,
//                                   @Query("tag") String tag,
//                                   @Query("start") String start,
//                                   @Query("end") String end);


}
