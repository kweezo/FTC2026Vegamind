package org.firstinspires.ftc.teamcode.shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    private DcMotor motor;
    private Servo magazine;
    public Shooter(DcMotor motor, Servo magazine) {
       this.motor = motor;
       this.magazine = magazine;

       motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void shoot(Gamepad gamepad) {
        motor.setPower(gamepad.left_trigger - gamepad.right_trigger);

        if(gamepad.dpad_left) {
            magazine.setPosition(-1.0f);
        } else if (gamepad.dpad_right) {
            magazine.setPosition(1.0f);
        }
    }
}

