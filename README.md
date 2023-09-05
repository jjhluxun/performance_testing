# 运行测试

## 运行所有测试
./gradlew gatlingRun

## 运行某个测试
./gradlew gatlingRun-AccessQRCodeSimulation.AccessCodeSimu
## 带参数运行某个测试
./gradlew gatlingRun-MixedScenario.MixedTestSimu -DuserCount=64 -DstartUserCount=8 -DrampDuringTime=30 -DduringTime=300 -DhostUrl='****'

./gradlew gatlingRun-SingleScenario.AuthSimu -DuserCount=32 -DstartUserCount=8 -DrampDuringTime=60 -DduringTime=300 

## 两种压测模式
混合压测和单接口压测