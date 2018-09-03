package com.lk.sf.smartfactoryworker.utils.diffutils

import android.support.v7.util.DiffUtil
import com.lk.sf.smartfactoryworker.bean.Device

/**
 * @author: winton
 * @time: 2018/8/3 16:38
 * @package: com.lk.sf.smartfactoryworker.utils.diffutils
 * @project: SmartFactoryWorker
 * @mail:
 * @describe:
 */
class BindDeviceDiffCallback:DiffUtil.ItemCallback<Device>() {

    override fun areItemsTheSame(oldItem: Device?, newItem: Device?): Boolean {

        return false
    }

    override fun areContentsTheSame(oldItem: Device?, newItem: Device?): Boolean {
        return false
    }
}