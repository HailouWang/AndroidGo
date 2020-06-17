package com.github.hailouwang.demosforapi.flutter.view;

import android.content.Context;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class PlatformNativeViewFactory extends PlatformViewFactory {
    /**
     * @param createArgsCodec the codec used to decode the args parameter of {@link #create}.
     */
    public PlatformNativeViewFactory(BinaryMessenger createArgsCodec) {
        super(StandardMessageCodec.INSTANCE);
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        return new PlatformNativeView(context, viewId, args);
    }

    public static void registerWith(PluginRegistry registry) {
        final String key = PlatformNativeViewFactory.class.getCanonicalName();

        if (registry.hasPlugin(key)) {
            return;
        }

        PluginRegistry.Registrar registrar = registry.registrarFor(key);
        registrar.platformViewRegistry().registerViewFactory("com.jzyd.sqkb_flutter/ttAdView", new PlatformNativeViewFactory(registrar.messenger()));
    }
}
