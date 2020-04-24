package com.github.hailouwang.demosforapi;

import com.android.build.gradle.AppExtension;
import com.quinn.hunter.transform.asm.BaseWeaver;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

class CodeInsertWeaver extends BaseWeaver {
    private final AppExtension mAppExtension;

    public CodeInsertWeaver(AppExtension appExtension) {
        mAppExtension = appExtension;
    }

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        System.out.println("CodeInsertWeaver wrapClassWriter -----------> classWriter : " + classWriter);
        return new CodeInsertClassAdapter(classWriter);
    }
}
