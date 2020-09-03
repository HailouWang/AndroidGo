package com.github.hailouwang.demosforapi.widget.slidegridview

import com.github.hailouwang.demosforapi.DemosApp


/**
 * dp px 转换工具类
 */
object DensityUtil {

    private val scale = DemosApp.context?.getResources()?.getDisplayMetrics()?.density

    /**
     * dp 转 px
     *
     * @param dpValue
     * @return
     */
    fun dip2px(dpValue: Float): Int {

        return (dpValue * scale!! + 0.5f).toInt()
    }

    /**
     * px 转 dp
     *
     * @param pxValue
     * @return
     */
    fun px2dip(pxValue: Float): Int {

        return (pxValue / scale!! + 0.5f).toInt()
    }


}
