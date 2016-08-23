package io.toru.rxpractive.network;

import io.toru.rxpractive.pattern.model.StackOverFlowQuestion;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by toru on 2016. 8. 21..
 */
public interface StackOverflowApi {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Observable<StackOverFlowQuestion> loadQuestions(@Query("tagged") String tags);
}