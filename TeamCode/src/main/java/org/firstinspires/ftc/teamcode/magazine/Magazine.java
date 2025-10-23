package org.firstinspires.ftc.teamcode.magazine;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Magazine {
    Servo chamberServo;
    Servo doorServo;

    boolean lastCircle;
    boolean lastX;

    public Magazine(Servo chamberServo, Servo doorServo) {
        this.chamberServo = chamberServo;
        this.doorServo = doorServo;

        lastCircle = false;
        lastX = false;
    }

    public void run(Gamepad gamepad) {
        if(gamepad.circle && !lastCircle) {
            chamberServo.setPosition(chamberServo.getPosition() == 1 ? 0 : 1);
        }

        if(gamepad.cross && !lastX) {
            doorServo.setPosition(doorServo.getPosition() == 1 ? 0 : 1);
        }

        lastX = gamepad.cross;
        lastCircle = gamepad.circle;
    }
}
