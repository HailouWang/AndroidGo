package com.github.hailouwang.demosforapi;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

class ApplicationInsertAdapter extends AdviceAdapter {

    protected ApplicationInsertAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor, String className) {
        super(Opcodes.ASM7, methodVisitor, access, name, descriptor);
        System.out.println("ApplicationInsertAdapter ApplicationInsertAdapter -----------> ");
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        System.out.println("ApplicationInsertAdapter onMethodEnter -----------> ");
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        System.out.println("ApplicationInsertAdapter onMethodExit -----------> opcode : " + opcode);
    }
}
