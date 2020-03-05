package org.firstinspires.ftc.teamcode._Test._Drive;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode._Libs.AutoLib;
import org.firstinspires.ftc.teamcode._Libs.BNO055IMUHeadingSensor;
import org.firstinspires.ftc.teamcode._Libs.TestHardware;

public class RobotHardware {

    public DcMotor[] mMotors;
    public DcMotor mLift;
    public DcMotor mWrist;
    public BNO055IMUHeadingSensor mIMU;
    public Servo mServo;

    public boolean init(OpMode opmode) {
        boolean bOkay = true;
        try {
            TestHardware.HardwareFactory mf = new TestHardware.RealHardwareFactory(opmode);

            // get the motors:
            // assumed order is fr, br, fl, bl
            mMotors = new DcMotor[4];
            mMotors[0] = mf.getDcMotor("fr");
            if (mMotors[0] != null) {
                mMotors[1] = mf.getDcMotor("br");
                (mMotors[2] = mf.getDcMotor("fl")).setDirection(DcMotor.Direction.REVERSE);
                (mMotors[3] = mf.getDcMotor("bl")).setDirection(DcMotor.Direction.REVERSE);
            }
            else {  // assume we're using the 2-wheel bot simulation
                mMotors[0] = mMotors[1] = mf.getDcMotor("right_motor");
                (mMotors[2] = mf.getDcMotor("left_motor")).setDirection(DcMotor.Direction.REVERSE);
                (mMotors[3] = mf.getDcMotor("left_motor")).setDirection(DcMotor.Direction.REVERSE);
            }

            // get lift and wrist motors --->>> need to decide on positive direction of motion for these ...
            mLift = mf.getDcMotor("lift");
            mWrist = mf.getDcMotor("wrist");

            // get hardware IMU and wrap gyro in HeadingSensor object usable below
            mIMU = new BNO055IMUHeadingSensor(opmode.hardwareMap.get(BNO055IMU.class, "imu"));
            mIMU.init(0);  // orientation of REV hub in my ratbot
            //mIMU.setDegreesPerTurn(355.0f);  // appears that's what my IMU does ... set this for your IMU

            // get grabber servo so we can test it
            mServo = mf.getServo("grabServo");
        }
        catch (IllegalArgumentException iax) {
            bOkay = false;
        }
        return bOkay;
    }
}
