#import "FlutterbatterypluginPlugin.h"
#if __has_include(<flutterbatteryplugin/flutterbatteryplugin-Swift.h>)
#import <flutterbatteryplugin/flutterbatteryplugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutterbatteryplugin-Swift.h"
#endif

@implementation FlutterbatterypluginPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterbatterypluginPlugin registerWithRegistrar:registrar];
}
@end
