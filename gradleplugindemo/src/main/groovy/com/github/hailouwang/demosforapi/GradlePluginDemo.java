package com.github.hailouwang.demosforapi;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

/**
 * AndroidStudio 插件： https://www.jianshu.com/p/da5920905380
 * Groovy 脚本：https://blog.csdn.net/yanbober/article/details/49047515
 */
public class GradlePluginDemo implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("GradlePluginDemo apply -----------> project : " + project);
        RepositoryHandler repositories = project.getRepositories();
        repositories.maven(new Action<MavenArtifactRepository>() {
            @Override
            public void execute(MavenArtifactRepository mavenArtifactRepository) {
                System.out.println("GradlePluginDemo apply -----------> MavenArtifactRepository : " + mavenArtifactRepository);
            }
        });
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        try {
            if (appExtension != null) {
                System.out.println("GradlePluginDemo apply -----------> appExtension : " + appExtension);
                appExtension.registerTransform(new CodeInsertTransform(project, appExtension));
            }
        } catch (Exception e) {

        }
    }
}
