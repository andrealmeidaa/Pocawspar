package br.edu.awsifrn.pojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
//Representa a API do Serviço
public interface IAndroidSample {
    @Headers({ "Accept: application/json" })
    @POST("/")
    Call<ResponseClass> getGreetings(@Body Person person);
}
