# Remote Proxy Pattern

Remote Proxy 패턴을 사용하여 원격 객체에 대한 접근을 제어하는 예제입니다.

## 패턴 개요

Remote Proxy는 다른 JVM(또는 다른 주소 공간)에 있는 객체의 로컬 대리자 역할을 합니다. 클라이언트는 원격 객체가 로컬에 있는 것처럼 메서드를 호출할 수 있으며, Proxy가 네트워크 통신을 처리합니다.

### 핵심 개념

- **Real Subject**: `GumballMachine` - 실제 원격 객체
- **Remote Interface**: `GumballMachineRemote` - Subject와 Proxy가 공유하는 인터페이스
- **Proxy**: RMI Stub (자동 생성) - 원격 객체에 대한 로컬 대리자
- **Client**: `GumballMonitor` - Proxy를 통해 원격 객체에 접근

## 패키지 구조

```
remote_proxy/
├── domain/                  # 도메인 모델 (Real Subject)
│   └── GumballMachine.java
│
├── remote/                  # RMI 인터페이스
│   └── GumballMachineRemote.java
│
├── state/                   # State 패턴 구현 (내부 구현)
│   ├── State.java
│   ├── NoQuarterState.java
│   ├── HasQuarterState.java
│   ├── SoldState.java
│   ├── SoldOutState.java
│   └── WinnerState.java
│
├── client/                  # 클라이언트 (Proxy 사용)
│   └── GumballMonitor.java
│
└── test/                    # 실행 예제
    ├── GumballMachineTestDrive.java
    └── GumballMonitorTestDrive.java
```

## 실행 방법

### 1. 프로젝트 컴파일

```bash
# 프로젝트 루트에서 실행
javac -d bin src/remote_proxy/**/*.java
```

### 2. RMI Registry 실행

RMI 레지스트리를 백그라운드에서 실행합니다.

```bash
# 별도의 터미널 창에서 실행
cd bin
rmiregistry &
```

또는

```bash
# Mac/Linux
rmiregistry -J-Djava.rmi.server.codebase=file:///path/to/bin/ &

# Windows
start rmiregistry
```

### 3. 서버(Real Subject) 실행

Gumball Machine 서버를 실행합니다.

```bash
# bin 디렉토리에서 실행
java remote_proxy.test.GumballMachineTestDrive localhost 100

# 형식: java remote_proxy.test.GumballMachineTestDrive <호스트명> <초기_검볼_개수>
```

**파라미터:**
- `<호스트명>`: RMI 레지스트리 호스트 (예: localhost)
- `<초기_검볼_개수>`: 초기 재고 수량

**예제:**
```bash
# 로컬호스트에 재고 100개로 시작
java remote_proxy.test.GumballMachineTestDrive localhost 100

# 여러 서버를 다른 위치에서 실행 가능
java remote_proxy.test.GumballMachineTestDrive seattle 50
java remote_proxy.test.GumballMachineTestDrive austin 200
```

### 4. 클라이언트(Monitor) 실행

별도의 터미널에서 모니터 클라이언트를 실행합니다.

```bash
# bin 디렉토리에서 실행
java remote_proxy.test.GumballMonitorTestDrive localhost

# 형식: java remote_proxy.test.GumballMonitorTestDrive <호스트명>
```

**파라미터:**
- `<호스트명>`: 모니터링할 Gumball Machine의 호스트명

**예제:**
```bash
# localhost의 Gumball Machine 모니터링
java remote_proxy.test.GumballMonitorTestDrive localhost
```

### 5. 종료

```bash
# RMI Registry 종료 (Mac/Linux)
pkill rmiregistry

# 또는 프로세스 ID로 종료
ps aux | grep rmiregistry
kill <PID>
```

## 실행 시나리오 예제

```bash
# Terminal 1: RMI Registry
cd bin
rmiregistry &

# Terminal 2: Gumball Machine 서버
cd bin
java remote_proxy.test.GumballMachineTestDrive localhost 100

# Terminal 3: Monitor 클라이언트
cd bin
java remote_proxy.test.GumballMonitorTestDrive localhost
```

**예상 출력:**
```
Gumball Machine: localhost
Current inventory: 100 gumballs
Current state: waiting for quarter
```

## 주요 클래스 설명

### 1. GumballMachine (Real Subject)
- 실제 비즈니스 로직을 가진 원격 객체
- `UnicastRemoteObject`를 상속하여 RMI 지원
- State 패턴으로 상태별 동작 구현

### 2. GumballMachineRemote (Remote Interface)
- Remote 인터페이스를 상속
- 원격으로 호출 가능한 메서드 정의
- 모든 메서드는 `RemoteException`을 던질 수 있음

### 3. GumballMonitor (Client)
- Proxy(RMI Stub)를 통해 원격 객체에 접근
- 원격 호출을 마치 로컬 호출처럼 사용
- 네트워크 예외 처리

## Remote Proxy 패턴의 장점

1. **위치 투명성**: 클라이언트는 객체가 로컬인지 원격인지 신경 쓰지 않음
2. **네트워크 추상화**: 복잡한 네트워크 통신을 Proxy가 처리
3. **분산 시스템**: 여러 JVM에 걸친 객체 간 통신 가능
4. **느슨한 결합**: 클라이언트와 서버가 독립적으로 배포 가능

## RMI 동작 원리

```
Client                    RMI Stub              RMI Skeleton            Real Object
  |                         |                        |                        |
  |--getCount()------------>|                        |                        |
  |                         |--serialize params----->|                        |
  |                         |                        |--getCount()---------->|
  |                         |                        |<------return 100------|
  |                         |<--deserialize result---|                        |
  |<------return 100--------|                        |                        |
```

1. 클라이언트가 Stub(Proxy)의 메서드 호출
2. Stub이 파라미터를 직렬화하여 네트워크로 전송
3. Skeleton이 요청을 받아 실제 객체의 메서드 호출
4. 결과를 직렬화하여 클라이언트에게 반환

## 문제 해결

### Port already in use
```bash
# 이미 실행 중인 rmiregistry 확인 및 종료
ps aux | grep rmiregistry
kill <PID>
```

### ClassNotFoundException
```bash
# codebase 설정하여 실행
java -Djava.rmi.server.codebase=file:///path/to/bin/ \
     remote_proxy.test.GumballMachineTestDrive localhost 100
```

### Connection refused
- RMI Registry가 실행 중인지 확인
- 서버가 먼저 실행되었는지 확인
- 방화벽 설정 확인

## 참고

- Head First Design Patterns - Remote Proxy 챕터
- Java RMI Documentation: https://docs.oracle.com/javase/tutorial/rmi/
