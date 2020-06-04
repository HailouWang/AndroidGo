package com.github.hailouwang.demosforapi.statemachine;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;
import com.hailouwang.statemachine.State;
import com.hailouwang.statemachine.StateMachine;

public class StateMachineHelloWorldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.state_machine_demo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.action).setOnClickListener(v -> {
            testHelloWorld();
        });
    }

    static class HelloWorld extends StateMachine {
        HelloWorld(String name) {
            super(name);
            addState(mState1);
            setInitialState(mState1);
        }

        public static HelloWorld makeHelloWorld() {
            HelloWorld hw = new HelloWorld("hlwang");
            hw.start();
            return hw;
        }

        class State1 extends State {
            @Override
            public boolean processMessage(Message msg) {
                log("Hello World");
                return HANDLED;
            }
        }

        State1 mState1 = new State1();
    }

    void testHelloWorld() {
        HelloWorld hw = HelloWorld.makeHelloWorld();
        hw.sendMessage(hw.obtainMessage());
    }
}
