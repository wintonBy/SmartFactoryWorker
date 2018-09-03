package com.lk.sf.smartfactoryworker.utils.diffutils

import android.support.v7.util.DiffUtil
import com.lk.sf.smartfactoryworker.bean.Caller

/**
 * @author: winton
 * @time: 2018/8/4 12:54
 * @package: com.lk.sf.smartfactoryworker.utils.diffutils
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 联系人不同
 */
class CallerDiffCallback:DiffUtil.ItemCallback<Caller>() {
    override fun areItemsTheSame(oldItem: Caller?, newItem: Caller?): Boolean {
        return  false
    }

    override fun areContentsTheSame(oldItem: Caller?, newItem: Caller?): Boolean {
        return  false
    }
}