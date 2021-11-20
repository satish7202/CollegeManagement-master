package com.ifanow.CollegeManagement.Services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ExternalApi {

	@POST("/api/student/select")
		Call<String> listRepos(@Body String sId);

}
