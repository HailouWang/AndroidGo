package com.hailouwang.modulization.templete

import android.app.Application

interface IModuleInitTemplate {

    @MethodExit
    fun attachBaseContextMethodExit(application: Application)

    @MethodEnter
    fun onCreateMethodEnter()

    @MethodExit
    fun onCreateMethodExit()
}