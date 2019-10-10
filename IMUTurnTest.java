package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "IMUTurnTest (Blocks to Java)", group = "")
public class IMUTurnTest extends LinearOpMode {

  private BNO055IMU imu;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    float CurrentHeading;
    Orientation angles;
    BNO055IMU.Parameters imuParameters;
    ElapsedTime TimerA;
    Acceleration gravity;

    imu = hardwareMap.get(BNO055IMU.class, "imu ");

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
        
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      while (CurrentHeading >= -78) {
        if (isStopRequested()) {
          break;
        }
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        CurrentHeading = angles.firstAngle;
        MecanumMovement(0, 0, 0.25);
        telemetry.addData("Curentheadingvalue", CurrentHeading);
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private void MecanumYeet(double YL, double XL, double XR) {
  }
}
