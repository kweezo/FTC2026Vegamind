package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    CRServo leftServo;
    CRServo rightServo;


    public Intake(CRServo leftServo, CRServo rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;

        leftServo.setDirection(DcMotorSimple.Direction.FORWARD);
        rightServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake(Gamepad gamepad) {
        int dir = (gamepad.dpad_up ? 1 : 0) - (gamepad.dpad_down ? 1 : 0);

        leftServo.setPower(dir);
        rightServo.setPower(dir);
    }
}
