// import 'package:plugin_platform_interface/plugin_platform_interface.dart';
//
// import 'light_method_channel.dart';
//
// abstract class LightPlatform extends PlatformInterface {
//   /// Constructs a LightPlatform.
//   LightPlatform() : super(token: _token);
//
//   static final Object _token = Object();
//
//   static LightPlatform _instance = MethodChannelLight();
//
//   /// The default instance of [LightPlatform] to use.
//   ///
//   /// Defaults to [MethodChannelLight].
//   static LightPlatform get instance => _instance;
//
//   /// Platform-specific implementations should set this with their own
//   /// platform-specific class that extends [LightPlatform] when
//   /// they register themselves.
//   static set instance(LightPlatform instance) {
//     PlatformInterface.verifyToken(instance, _token);
//     _instance = instance;
//   }
//
//   Future<String?> getPlatformVersion() {
//     throw UnimplementedError('platformVersion() has not been implemented.');
//   }
// }
