// import 'package:flutter_test/flutter_test.dart';
// import 'package:light/light.dart';
// import 'package:light/light_platform_interface.dart';
// import 'package:light/light_method_channel.dart';
// import 'package:plugin_platform_interface/plugin_platform_interface.dart';
//
// class MockLightPlatform
//     with MockPlatformInterfaceMixin
//     implements LightPlatform {
//
//   @override
//   Future<String?> getPlatformVersion() => Future.value('42');
// }
//
// void main() {
//   final LightPlatform initialPlatform = LightPlatform.instance;
//
//   test('$MethodChannelLight is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelLight>());
//   });
//
//   test('getPlatformVersion', () async {
//     Light lightPlugin = Light();
//     MockLightPlatform fakePlatform = MockLightPlatform();
//     LightPlatform.instance = fakePlatform;
//
//     expect(await lightPlugin.getPlatformVersion(), '42');
//   });
// }
