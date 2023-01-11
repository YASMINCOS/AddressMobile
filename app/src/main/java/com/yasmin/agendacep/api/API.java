package com.yasmin.agendacep.api;

import com.yasmin.agendacep.model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    @GET("ws/{cep}/json/")
    Call<Address> getAddress(@Path("cep") String cep);

}
