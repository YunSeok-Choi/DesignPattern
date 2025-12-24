package test;

import command.Command;
import command.MacroCommand;
import command.ceilingfan.CeilingFanHighCommand;
import command.ceilingfan.CeilingFanMediumCommand;
import command.ceilingfan.CeilingFanOffCommand;
import command.garagedoor.GarageDoorDownCommand;
import command.garagedoor.GarageDoorUpCommand;
import command.hottub.HottubOffCommand;
import command.hottub.HottubOnCommand;
import command.light.LightOffCommand;
import command.light.LightOnCommand;
import command.stereo.StereoOffCommand;
import command.stereo.StereoOnCommand;
import command.stereo.StereoOnWithCDCommand;
import command.tv.TVOffCommand;
import command.tv.TVOnCommand;
import invoker.RemoteControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import receiver.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {

    private RemoteControl remoteControl;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Receivers
    private Light livingRoomLight;
    private Light kitchenLight;
    private CeilingFan ceilingFan;
    private GarageDoor garageDoor;
    private Stereo stereo;
    private TV tv;
    private Hottub hottub;

    // Light Commands
    private LightOnCommand livingRoomLightOn;
    private LightOffCommand livingRoomLightOff;
    private LightOnCommand kitchenLightOn;
    private LightOffCommand kitchenLightOff;

    // CeilingFan Commands
    private CeilingFanHighCommand ceilingFanHigh;
    private CeilingFanMediumCommand ceilingFanMedium;
    private CeilingFanOffCommand ceilingFanOff;

    // GarageDoor Commands
    private GarageDoorUpCommand garageDoorUp;
    private GarageDoorDownCommand garageDoorDown;

    // Stereo Commands
    private StereoOnCommand stereoOn;
    private StereoOffCommand stereoOff;
    private StereoOnWithCDCommand stereoOnWithCD;

    // TV Commands
    private TVOnCommand tvOn;
    private TVOffCommand tvOff;

    // Hottub Commands
    private HottubOnCommand hottubOn;
    private HottubOffCommand hottubOff;

    @BeforeEach
    public void setUp() {
        remoteControl = new RemoteControl();

        // Initialize Receivers
        livingRoomLight = new Light("Living Room");
        kitchenLight = new Light("Kitchen");
        ceilingFan = new CeilingFan("Living Room");
        garageDoor = new GarageDoor("Garage");
        stereo = new Stereo("Living Room");
        tv = new TV("Living Room");
        hottub = new Hottub();

        // Initialize Light Commands
        livingRoomLightOn = new LightOnCommand(livingRoomLight);
        livingRoomLightOff = new LightOffCommand(livingRoomLight);
        kitchenLightOn = new LightOnCommand(kitchenLight);
        kitchenLightOff = new LightOffCommand(kitchenLight);

        // Initialize CeilingFan Commands
        ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
        ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        // Initialize GarageDoor Commands
        garageDoorUp = new GarageDoorUpCommand(garageDoor);
        garageDoorDown = new GarageDoorDownCommand(garageDoor);

        // Initialize Stereo Commands
        stereoOn = new StereoOnCommand(stereo);
        stereoOff = new StereoOffCommand(stereo);
        stereoOnWithCD = new StereoOnWithCDCommand(stereo);

        // Initialize TV Commands
        tvOn = new TVOnCommand(tv);
        tvOff = new TVOffCommand(tv);

        // Initialize Hottub Commands
        hottubOn = new HottubOnCommand(hottub);
        hottubOff = new HottubOffCommand(hottub);
    }

    // Helper Methods for Output Capture
    private void startCapture() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    private String stopCaptureAndGetOutput() {
        System.setOut(originalOut);
        return outContent.toString();
    }

    private void assertOutputContains(String expected, String message) {
        String output = outContent.toString();
        assertTrue(output.contains(expected), message);
    }

    @Test
    public void living_room_macro_test() {
        // Given
        Command[] partyOn = {livingRoomLightOn, stereoOn, tvOn, hottubOn};
        Command[] partyOff = {livingRoomLightOff, stereoOff, tvOff, hottubOff};
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);
        remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

        // When - 파티 모드 켜기
        startCapture();
        remoteControl.onButtonWasPressed(0);
        stopCaptureAndGetOutput();

        // Then - 파티 모드 켜기 출력 검증
        assertOutputContains("Living Room 조명이 켜졌습니다", "조명이 켜져야 합니다");
        assertOutputContains("Living Room 오디오가 켜졌습니다", "오디오가 켜져야 합니다");
        assertOutputContains("Living Room TV가 켜졌습니다", "TV가 켜져야 합니다");
        assertOutputContains("욕조가 105도까지 가열되고 있습니다", "욕조가 가열되어야 합니다");
        assertOutputContains("욕조에 거품이 생성되고 있습니다", "욕조 거품이 생성되어야 합니다");

        // When - 파티 모드 끄기
        startCapture();
        remoteControl.offButtonWasPressed(0);
        stopCaptureAndGetOutput();

        // Then - 파티 모드 끄기 출력 검증
        assertOutputContains("Living Room 조명이 꺼졌습니다", "조명이 꺼져야 합니다");
        assertOutputContains("Living Room 오디오가 꺼졌습니다", "오디오가 꺼져야 합니다");
        assertOutputContains("Living Room TV가 꺼졌습니다", "TV가 꺼져야 합니다");
        assertOutputContains("욕조가 98도로 냉각되고 있습니다", "욕조가 냉각되어야 합니다");

        // When - Undo (파티 모드 다시 켜기)
        startCapture();
        remoteControl.undoButtonWasPressed();
        stopCaptureAndGetOutput();

        // Then - Undo 출력 검증 (모든 기기가 다시 켜져야 함)
        assertOutputContains("Living Room 조명이 켜졌습니다", "Undo 후 조명이 다시 켜져야 합니다");
        assertOutputContains("Living Room 오디오가 켜졌습니다", "Undo 후 오디오가 다시 켜져야 합니다");
        assertOutputContains("Living Room TV가 켜졌습니다", "Undo 후 TV가 다시 켜져야 합니다");
        assertOutputContains("욕조가 105도까지 가열되고 있습니다", "Undo 후 욕조가 다시 가열되어야 합니다");
        assertOutputContains("욕조에 거품이 생성되고 있습니다", "Undo 후 욕조 거품이 다시 생성되어야 합니다");
    }

    @Test
    public void remote_control_with_various_devices_test() {
        // Given - 커맨드 설정
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanMedium, ceilingFanOff);
        remoteControl.setCommand(3, ceilingFanHigh, ceilingFanOff);
        remoteControl.setCommand(4, stereoOnWithCD, stereoOff);

        // When - 조명 테스트
        startCapture();
        remoteControl.onButtonWasPressed(0);
        remoteControl.offButtonWasPressed(0);
        remoteControl.onButtonWasPressed(1);
        remoteControl.offButtonWasPressed(1);
        stopCaptureAndGetOutput();

        // Then - 조명 출력 검증
        assertOutputContains("Living Room 조명이 켜졌습니다", "Living Room 조명이 켜져야 합니다");
        assertOutputContains("Living Room 조명이 꺼졌습니다", "Living Room 조명이 꺼져야 합니다");
        assertOutputContains("Kitchen 조명이 켜졌습니다", "Kitchen 조명이 켜져야 합니다");
        assertOutputContains("Kitchen 조명이 꺼졌습니다", "Kitchen 조명이 꺼져야 합니다");

        // When - 선풍기 중 속도 설정
        startCapture();
        remoteControl.onButtonWasPressed(2);
        stopCaptureAndGetOutput();

        // Then - 선풍기 중 속도 검증
        assertEquals(CeilingFan.MEDIUM, ceilingFan.getSpeed(), "선풍기는 중 속도여야 합니다");
        assertOutputContains("Living Room 천장 선풍기가 중으로 설정되었습니다", "선풍기가 중으로 설정되어야 합니다");

        // When - 선풍기 끄기
        startCapture();
        remoteControl.offButtonWasPressed(2);
        stopCaptureAndGetOutput();

        // Then - 선풍기 꺼짐 검증
        assertEquals(CeilingFan.OFF, ceilingFan.getSpeed(), "선풍기는 꺼져야 합니다");
        assertOutputContains("Living Room 천장 선풍기가 꺼졌습니다", "선풍기가 꺼져야 합니다");

        // When - 선풍기 Undo (중으로 복원)
        startCapture();
        remoteControl.undoButtonWasPressed();
        stopCaptureAndGetOutput();

        // Then - 선풍기 중으로 복원 검증
        assertEquals(CeilingFan.MEDIUM, ceilingFan.getSpeed(), "Undo 후 선풍기는 다시 중 속도여야 합니다");
        assertOutputContains("Living Room 천장 선풍기가 중으로 설정되었습니다", "Undo 후 선풍기가 중으로 돌아가야 합니다");

        // When - 선풍기 강 속도 설정
        startCapture();
        remoteControl.onButtonWasPressed(3);
        stopCaptureAndGetOutput();

        // Then - 선풍기 강 속도 검증
        assertEquals(CeilingFan.HIGH, ceilingFan.getSpeed(), "선풍기는 강 속도여야 합니다");
        assertOutputContains("Living Room 천장 선풍기가 강으로 설정되었습니다", "선풍기가 강으로 설정되어야 합니다");

        // When - 선풍기 Undo (중으로 복원)
        startCapture();
        remoteControl.undoButtonWasPressed();
        stopCaptureAndGetOutput();

        // Then - 선풍기 이전 상태(중) 복원 검증
        assertEquals(CeilingFan.MEDIUM, ceilingFan.getSpeed(), "Undo 후 선풍기는 이전 상태(중)로 돌아가야 합니다");
        assertOutputContains("Living Room 천장 선풍기가 중으로 설정되었습니다", "Undo 후 선풍기가 중으로 돌아가야 합니다");

        // When - 오디오 테스트
        startCapture();
        remoteControl.onButtonWasPressed(4);
        remoteControl.offButtonWasPressed(4);
        stopCaptureAndGetOutput();

        // Then - 오디오 출력 검증
        assertOutputContains("Living Room 오디오가 켜졌습니다", "오디오가 켜져야 합니다");
        assertOutputContains("Living Room 오디오 CD 입력으로 설정되었습니다", "오디오가 CD로 설정되어야 합니다");
        assertOutputContains("Living Room 오디오 볼륨이 11로 설정되었습니다", "오디오 볼륨이 11로 설정되어야 합니다");
        assertOutputContains("Living Room 오디오가 꺼졌습니다", "오디오가 꺼져야 합니다");
    }

}
