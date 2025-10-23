package org.firstinspires.ftc.teamcode.drivetrain;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
public class FieldCentricDrivetrain extends Drivetrain {
    public FieldCentricDrivetrain(HardwareMap hardwareMap, IMU imu, Motors motors) {
        super(hardwareMap, imu, motors);
    }

    @Override
    public void run(Gamepad gamepad) {

    }

    public void run(double inputX, double inputY, double inputRot) {
        double robotDirection = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = inputX * Math.cos(-robotDirection) - inputY * Math.sin(-robotDirection);
        double rotY = inputX * Math.sin(-robotDirection) + inputY * Math.cos(-robotDirection);

        Pose2d move = new Pose2d(
                rotX,
                rotY,
                inputRot
        );

        drive(move);
    }

    public void run(Gamepad gamepad, boolean flipped) {
        //if (map.isImuReset()) imu.resetYaw();

        double x = gamepad.left_stick_x;
        double y = gamepad.left_stick_y;
        double rot = gamepad.right_stick_x;

        if (flipped) {
            y *= -1;
            x *= -1;
        }

        run(-x, -y, rot);
    }

    public void drive(Pose2d pose2d) {
        this.setDrivePower(pose2d);
    }
}
