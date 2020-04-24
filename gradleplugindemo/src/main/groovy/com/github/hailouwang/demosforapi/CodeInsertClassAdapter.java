package com.github.hailouwang.demosforapi;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class CodeInsertClassAdapter extends ClassVisitor {
    private String mClassName;
    private String mSuperName;

    public CodeInsertClassAdapter(ClassWriter classWriter) {
        super(Opcodes.ASM7, classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("CodeInsertClassAdapter visit -----------> version : " + version
                + ", access ： " + access
                + ", name : " + name
                + ", signature : " + signature
                + ", superName : " + superName
                + ", interfaces : " + interfaces);
        mClassName = name;
        mSuperName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        //从传进来的ClassWriter中读取MethodVisitor
        try {
            System.out.println("CodeInsertClassAdapter visitMethod -----------> access : " + access
                    + ", name : " + name
                    + ", descriptor : " + descriptor
                    + ", signature : " + signature
                    + ", exceptions : " + exceptions);
            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
            if (name != null && name.equals("onCreate")) {
                return new ApplicationInsertAdapter(mv, access, name, descriptor, mClassName);
            }
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
