package org.firstinspires.ftc.teamcode.magazine;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.color.BallColor;
import org.firstinspires.ftc.teamcode.color.HSV;
import org.firstinspires.ftc.teamcode.input.PrimaryMap;

public class Magazine {
    Servo magazineServo;
    Servo gateServo;

    int currIndex = -1;

    int gateClosePos= 0;
    int gateOpenPos = 1;

    final int[] magazineBallPositions = {0, 1, 2}; //TODO replace
    ColorSensor[] sensors = new ColorSensor[3];

    public Magazine(Servo magazineServo, Servo gateServo, ColorSensor[] sensors) {
        this.magazineServo = magazineServo;
        this.gateServo = gateServo;
        this.sensors = sensors;
    }

    public void rotateToBall(int index) {
        magazineServo.setPosition(magazineBallPositions[index]);
        currIndex = -1;
    }

    /// returns true if magazine is at the correct position
    /// in which case the gate will open, otherwise it wont
    public boolean openGate() {
        if (!atTargetBall()) {
            return false;
        }

        magazineServo.setPosition(gateOpenPos);

        return true;
    }

    public void closeGate() {
        magazineServo.setPosition(gateClosePos);
    }


    public boolean atTargetBall() {
        if(currIndex == -1) return false;

        return magazineServo.getPosition() == magazineBallPositions[currIndex]; // TODO add a margin for error?
    }

    public BallColor getBallAtSlot(int index) {
        int r = sensors[index].red();
        int g = sensors[index].green();
        int b = sensors[index].blue();

        HSV color = new HSV(r, g, b);

        if(color.v() < 0.3) return BallColor.NONE;

        if (color.h() < 180) return BallColor.GREEN; //good enough-ish
        else return BallColor.PURPLE;
    }
}
