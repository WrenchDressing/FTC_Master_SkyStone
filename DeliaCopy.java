package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import java.lang.annotation.Target;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "DeliaCopy", group = "")
public class DeliaCopy extends LinearOpMode {

  private BNO055IMU imu;
  private DcMotor motor_drive_br;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_bl;

  float CurrentHeading;
  Orientation angles;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    BNO055IMU.Parameters imuParameters;
    ElapsedTime TimerA;
    Acceleration gravity;

    imu = hardwareMap.get(BNO055IMU.class, "imu");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");

    imuParameters = new BNO055IMU.Parameters();
    // Use degrees as angle unit.
    TimerA = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    // Express acceleration as m/s^2.
    imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    // Disable logging.
    imuParameters.loggingEnabled = false;
    // Initialize IMU.
    imu.initialize(imuParameters);
    // Prompt user to press start buton.
    telemetry.addData("IMU Example", "Press start to continue...");
    telemetry.update();
    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    gravity = imu.getGravity();
    CurrentHeading = angles.firstAngle;
    telemetry.addData("Done with IMU", "Done with motor init");
    telemetry.update();
    motor_drive_br.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_fr.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_fl.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
    }
  }

  /**
   * Describe this function...
   */
  private void MecanumYeet(double YL, double XL, double XR) {
    motor_drive_fl.setPower(YL - (XL + XR));
    motor_drive_bl.setPower(YL + (XL - XR));
    motor_drive_fr.setPower(YL + XL + XR);
    motor_drive_br.setPower(YL - (XL - XR));
  }

  /**
   * Describe this function...
   */
  private void IMUTurn(double TrgtAngle, double TurnAngle) {
    AngleTurn = TrgtAngle - CurrentHeading;
    if (TrgtAngle < 0) {
      while (TrgtAngle + 3 <= CurrentHeading) {
        if (isStopRequested()) {
          break;
        }
        if (TrgtAngle - CurrentHeading < -60) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, 1);
          telemetry.addData("Curentheadingvalue", CurrentHeading);
          telemetry.update();
        } else if (TrgtAngle - CurrentHeading >= -60) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, 0.2);
          telemetry.addData("Curentheadingvalue", CurrentHeading);
          telemetry.update();
        }
      }
    } else if (TrgtAngle > 0) {
      while (TrgtAngle - 3 >= CurrentHeading) {
        if (isStopRequested()) {
          break;
        }
        if (TrgtAngle - CurrentHeading > 60) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, -1);
          telemetry.addData("Curentheadingvalue", CurrentHeading);
          telemetry.update();
        } else if (TrgtAngle - CurrentHeading <= 60) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, -0.2);
          telemetry.addData("Curentheadingvalue", CurrentHeading);
          telemetry.update();
        }
      } 
    } else {
      if (isStopRequested()) {
          break;
        }
      if (AngleTurn > 0){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        CurrentHeading = angles.firstAngle;
        MecanumYeet(0, 0, -1);
        telemetry.addData("Curentheadingvalue", CurrentHeading);
        telemetry.update();
    } else if (AngleTurn < 0){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        CurrentHeading = angles.firstAngle;
        MecanumYeet(0, 0, -0.2);
        telemetry.addData("Curentheadingvalue", CurrentHeading);
        telemetry.update();
      }
    
    MecanumYeet(0, 0, 0);
    }
  }
}
