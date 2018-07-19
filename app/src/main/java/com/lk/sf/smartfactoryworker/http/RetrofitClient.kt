package com.lk.sf.smartfactoryworker.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.lk.sf.smartfactoryworker.MyApplication
import com.lk.sf.smartfactoryworker.constant.DeveloperConfig
import com.lk.sf.smartfactoryworker.http.response.UpdateInfoResponse
import com.lk.sf.smartfactoryworker.utils.ScheduelHelper
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: winton
 * @time: 2018/7/12 23:33
 * @package: com.lk.sf.smartfactoryworker.http
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 网络引擎
 */
object RetrofitClient {
    private const val DEFAULT_TIMEOUT = 15L

    private lateinit var server:ApiServer
    init {
        var cookJar = PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(MyApplication.instance))
        var okBuilder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .cookieJar(cookJar)

        if(DeveloperConfig.isDebug){
            okBuilder.addInterceptor(LoggerInterceptor("OKHttp"))
        }
        var okHttpClient = okBuilder.build()
        var retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiServer.host)
                .build()
        server = retrofit.create(ApiServer::class.java)
    }

    /*********************************接口方法********************************/

    fun checkUpdate(subscriber: FlowableSubscriber<UpdateInfoResponse>){
        server.updateInfo()
                .compose(ScheduelHelper.compose())
                .subscribe(subscriber)
    }

}