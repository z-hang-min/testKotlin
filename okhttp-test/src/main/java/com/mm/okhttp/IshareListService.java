package com.mm.okhttp;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * created by zm on 2023/2/14
 *
 * @Description:
 */
class IshareListService {
    @GET("app/account/checkLogin")
    retrofit2.Call<String> checkLogin(@Query("mobile") String mobile, @Query("sign") String sign) {
        return null;
    }
}
