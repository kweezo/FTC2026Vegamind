package org.firstinspires.ftc.teamcode.manager;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.color.BallColor;
import org.firstinspires.ftc.teamcode.magazine.Magazine;
import org.firstinspires.ftc.teamcode.shooter.Shooter;

public class ShooterManager {

    enum State {
        INACTIVE,
        BALL_SELECT,
        WINDUP,
        SHOOT,
        WINDDOWN,
    }
    int gPos;
    int currSlot = 0;

    Magazine magazine;
    Shooter shooter;

    boolean isWindingUp = false;



    State currState = State.INACTIVE;

    ElapsedTime shotTimer = new ElapsedTime();
    ElapsedTime windupTimer = new ElapsedTime();

    final int shootTime = 1500;
    final int windupTime = 3000; // idk smthn

    public ShooterManager(Magazine magazine, Shooter shooter, int tagID) {
        gPos = tagID - 21;
        //https://ftc-resources.firstinspires.org/ftc/game/manual-10 - page 8

        this.magazine = magazine;
        this.shooter = shooter;
    }

    int getNextMagSlot() {
        BallColor targetColor = BallColor.PURPLE;
        if(currSlot % 3 == gPos)
            targetColor = BallColor.GREEN;

        for(int i = 0; i < 3; i++) {
            if(targetColor == magazine.getBallAtSlot(i))
                return i;
        }

        return -1; //TODO how to handle no ball
    }

    public int getWindupTime() {
        return windupTime;
    }

    public boolean start() {
        if(currState != State.INACTIVE) return false;

        currState = State.BALL_SELECT;
        return true;
    }

    public void stop() {
        currState = State.WINDDOWN;
    }

    public boolean shoot() {
        if(currState.ordinal() != State.SHOOT.ordinal() - 1) return false;

        currState = State.SHOOT;
        return true;
    }

    void windupState() {
        shooter.windup();
        windupTimer.startTime();
    }

    void ballSelectState() {
        currState = State.BALL_SELECT;
        magazine.rotateToBall(
                getNextMagSlot()
        );
    }

    void shootState() {
        if (!magazine.openGate()
        || windupTimer.milliseconds() < windupTime) return;

        currState = State.WINDDOWN;
        shotTimer.startTime();
        currSlot++;
    }

    void windDownState() {
        if(shotTimer.milliseconds() < shootTime) return;

        magazine.closeGate();
        shooter.winddown();

        currState = State.INACTIVE;
    }

    void update() {
        switch(currState) {
            case BALL_SELECT:
                ballSelectState();
                break;
            case WINDUP:
                windupState();
                break;
            case WINDDOWN:
                windDownState();
                break;
            default:
        }
    }
}
