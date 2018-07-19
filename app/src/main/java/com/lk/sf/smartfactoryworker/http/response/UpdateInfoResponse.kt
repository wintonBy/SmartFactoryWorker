package com.lk.sf.smartfactoryworker.http.response

import com.lk.sf.smartfactoryworker.bean.UpdateInfo
import com.lk.sf.smartfactoryworker.http.BaseResponse
import com.lk.sf.smartfactoryworker.http.BaseSubscriber
import com.lk.sf.smartfactoryworker.http.RetrofitClient

/**
 * @author: winton
 * @time: 2018/7/13 10:22
 * @package: com.lk.sf.smartfactoryworker.http.response
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 版本检测响应类
 */
class UpdateInfoResponse:BaseResponse() {
    var data:UpdateInfo? = null
}