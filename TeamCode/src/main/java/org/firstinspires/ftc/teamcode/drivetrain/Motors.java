package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Encoder;

public class Motors {
    public Motors(HardwareMap hardwareMap, String rearLeftName, String rearRightName, String frontLeftName, String frontRightName) {
        frontRight = hardwareMap.get(DcMotor.class, frontRightName);
        frontLeft = hardwareMap.get(DcMotor.class, frontLeftName);
        rearLeft = hardwareMap.get(DcMotor.class, rearLeftName);
        rearRight = hardwareMap.get(DcMotor.class, rearRightName);

        frontRightEx = hardwareMap.get(DcMotorEx.class, frontRightName);
        frontLeftEx = hardwareMap.get(DcMotorEx.class, frontLeftName);
        rearLeftEx = hardwareMap.get(DcMotorEx.class, rearLeftName);
        rearRightEx = hardwareMap.get(DcMotorEx.class, rearRightName);
    }

    public DcMotor rearLeft;
    public DcMotor rearRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotorEx rearLeftEx;
    public DcMotorEx rearRightEx;
    public DcMotorEx frontLeftEx;
    public DcMotorEx frontRightEx;
}
