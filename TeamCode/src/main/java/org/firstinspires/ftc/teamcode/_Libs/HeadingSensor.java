package org.firstinspires.ftc.teamcode._Libs;

/**
 * Created by phanau on 10/31/16.
 */
public interface HeadingSensor {

    // return current heading reported by sensor, in degrees.
    // convention is positive angles CCW, wrapping from 359-0
    public float getHeading();          // get current absolute gyro heading
    public float getHeadingVelocity();          // get rate of change of current absolute gyro heading
    public boolean haveHeading();       // is there valid heading data?
    public void setHeadingOffset(float offset);     // set absolute field heading corresponding to initial (zero) gyro reading
}
