package com.github.hailouwang.demosforapi

import com.android.build.gradle.AppExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

/**
 * AndroidStudio 插件： https://www.jianshu.com/p/da5920905380
 * Groovy 脚本：https://blog.csdn.net/yanbober/article/details/49047515
 * <p>
 * <p>
 * https://google.github.io/android-gradle-dsl/current/com.android.build.gradle.AppExtension.html
 * <p>
 * https://www.jianshu.com/p/ffd6153ace1d
 */
class GradleTestDemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("hlwang =====> GradlePluginDemo apply -----------> project : " + project);
    }
}