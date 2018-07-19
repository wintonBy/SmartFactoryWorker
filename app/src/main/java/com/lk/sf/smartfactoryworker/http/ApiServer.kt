package com.lk.sf.smartfactoryworker.http

import com.lk.sf.smartfactoryworker.constant.DeveloperConfig
import com.lk.sf.smartfactoryworker.http.response.UpdateInfoResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.POST



/**
 * @author: winton
 * @time: 2018/7/9 20:36
 * @package: com.lk.sf.smartfactoryworker.http
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 服务端接口
 */
interface ApiServer {

    companion object {
        var host = "https://leadtotech.com/mes-cnc/"
    }

    /**
     * 版本信息
     * @return
     */
    @POST("rest/common/app/version")
    fun updateInfo(): Flowable<UpdateInfoResponse>

}