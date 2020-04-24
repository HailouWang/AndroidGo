package com.github.hailouwang.demosforapi;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.AppExtension;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.quinn.hunter.transform.HunterTransform;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

class CodeInsertTransform extends HunterTransform {

    private final AppExtension mAppExtension;

    public CodeInsertTransform(Project project, AppExtension appExtension) {
        super(project);
        mAppExtension = appExtension;
        this.bytecodeWeaver = new CodeInsertWeaver(mAppExtension);
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
        System.out.println("CodeInsertTransform transform -----------> inputs : " + inputs
                + ", referencedInputs : " + referencedInputs
                + ", outputProvider : " + outputProvider
                + ", isIncremental : " + isIncremental);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }
}
