package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class TankDrive extends Drivetrain {

    public TankDrive(HardwareMap hardwareMap, Motors motors) {
        super(hardwareMap, hardwareMap.get(IMU.class, "imu"), motors);
        motors.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run(Gamepad gamepad) {
        motors.rearRight.setPower(gamepad.left_stick_y);
        motors.frontRight.setPower(gamepad.left_stick_y);

        motors.rearLeft.setPower(-gamepad.right_stick_y);
        motors.frontLeft.setPower(-gamepad.right_stick_y);
    }
}
