package com.ifanow.CollegeManagement.Services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Query;

public interface ExternalApi {

	@HTTP(method = "POST", path = "/api/student/select", hasBody = true)
		Call<String> listRepos(@Body String sId);

}
