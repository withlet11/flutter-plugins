// import 'package:flutter/foundation.dart';
// import 'package:flutter/services.dart';
//
// import 'light_platform_interface.dart';
//
// /// An implementation of [LightPlatform] that uses method channels.
// class MethodChannelLight extends LightPlatform {
//   /// The method channel used to interact with the native platform.
//   @visibleForTesting
//   final methodChannel = const MethodChannel('light');
//
//   @override
//   Future<String?> getPlatformVersion() async {
//     final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
//     return version;
//   }
// }
