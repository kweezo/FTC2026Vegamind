package org.firstinspires.ftc.teamcode.color;

//in case something don't work
//
public class HSV {
    double h = 0, s = 0, v = 0;

    public HSV(int r, int g, int b) {
        double rNormal = r / 255.0f;
        double gNormal = g / 255.0f;
        double bNormal = b / 255.0f;

        double max = Math.max(Math.max(rNormal, gNormal), bNormal);
        double min = Math.min(Math.min(rNormal, gNormal), bNormal);

        double delta = max - min;

        if(max == rNormal)
            h = (gNormal - bNormal) / delta;
         else if (max == gNormal)
            h = (bNormal - rNormal) / delta + 2;
         else
            h = (rNormal - gNormal) / delta + 4;

         h *= 360;

        v = max;
        if(v == 0)
            s = 0;
        else
            s = delta / v;
    }

    public double h() {
        return h;
    }

    public double s() {
        return s;
    }

    public double v() {
        return v;
    }
}
