package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;

@Autonomous(name = "BlockSideBlue (Blocks to Java)", group = "")
public class BlockSideBlue extends LinearOpMode {

  private Servo FoundationServoL;
  private Servo FoundationServoR;
  private Servo GrabberL;
  private Servo GrabberR;
  private DcMotor motor_drive_fl;
  private DcMotor WristMotor;
  private VuforiaSkyStone vuforiaSkyStone;
  private DcMotor LiftyMotor;
  private BNO055IMU imu;
  private DcMotor motor_drive_br;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_bl;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    FoundationServoL = hardwareMap.servo.get("FoundationServoL");
    FoundationServoR = hardwareMap.servo.get("FoundationServoR");
    GrabberL = hardwareMap.servo.get("GrabberL");
    GrabberR = hardwareMap.servo.get("GrabberR");
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    WristMotor = hardwareMap.dcMotor.get("WristMotor");
    vuforiaSkyStone = new VuforiaSkyStone();
    LiftyMotor = hardwareMap.dcMotor.get("LiftyMotor");
    imu = hardwareMap.get(BNO055IMU.class, "imu");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");

    Initialization();
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      FoundationServoL.setPosition(0);
      FoundationServoR.setPosition(1);
      Distance_Move(13.25, 0.4);
      GrabberL.setPosition(0.6);
      GrabberR.setPosition(0.6);
      motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      while (!(isVisible == true)) {
        MecanumYeet(0, -0.1, 0);
        if (isStopRequested()) {
          break;
        }
        // Are the targets visible?
        // (Note we only process first visible target).
        if (isTargetVisible("Stone Target")) {
          processTarget();
        } else if (isTargetVisible("Blue Front Bridge")) {
          processTarget();
        } else if (isTargetVisible("Red Rear Bridge")) {
          processTarget();
        } else if (isTargetVisible("Red Front Bridge")) {
          processTarget();
        } else if (isTargetVisible("Red Rear Bridge")) {
          processTarget();
        } else if (isTargetVisible("Red Perimeter 1")) {
          processTarget();
        } else if (isTargetVisible("Red Perimeter 2")) {
          processTarget();
        } else if (isTargetVisible("Front Perimeter 1")) {
          processTarget();
        } else if (isTargetVisible("Front Perimeter 2")) {
          processTarget();
        } else if (isTargetVisible("Blue Perimeter 1")) {
          processTarget();
        } else if (isTargetVisible("Blue Perimeter 2")) {
          processTarget();
        } else if (isTargetVisible("Rear Perimeter 1")) {
          processTarget();
        } else if (isTargetVisible("Rear Perimeter 2")) {
          processTarget();
        } else {
          telemetry.addData("No Targets Detected", "Targets are not visible.");
        }
        telemetry.update();
      }
      StrafeOffset = motor_drive_fl.getCurrentPosition();
      WristMotor.setTargetPosition(490);
      WristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      WristMotor.setPower(0.6);
      vuforiaSkyStone.deactivate();
      MecanumYeet(0, 0, 0);
      telemetry.addData("Inaccuracy (In)", Inaccuracy);
      telemetry.update();
      if (Inaccuracy > 0) {
        Distance_MoveStrafe(-(Inaccuracy - 2.65), 0.25);
      } else if (Inaccuracy < 0) {
        Distance_MoveStrafe(-(Inaccuracy + 2.65), -0.25);
      }
      Offset = motor_drive_fl.getCurrentPosition();
      Distance_Move(7, 0.5);
      sleep(150);
      GrabberR.setPosition(1);
      GrabberL.setPosition(1);
      sleep(150);
      Distance_Move(-4.25, -1);
      motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      sleep(100);
      while (motor_drive_fl.getCurrentPosition() - 500 > -StrafeOffset) {
        MecanumYeet(0, 0.5, 0);
      }
      sleep(200);
      IMUTurn(-90);
      Distance_Move(56, 1);
      LiftyMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      while (LiftyMotor.getCurrentPosition() > -2150) {
        LiftyMotor.setPower(-1);
      }
      LiftyMotor.setPower(0);
      IMUTurn(0);
      Distance_Move(7, 0.2);
      GrabberR.setPosition(0.6);
      GrabberL.setPosition(0.6);
      sleep(250);
      FoundationServoL.setPosition(1);
      FoundationServoR.setPosition(0);
      GrabberR.setPosition(0);
      GrabberL.setPosition(0);
      sleep(250);
      Distance_MoveStrafe(26, 0.75);
      IMUTurn(-178);
      Distance_Move(20, 0.75);
      FoundationServoL.setPosition(0);
      FoundationServoR.setPosition(1);
      sleep(250);
      Distance_MoveStrafe(5, -1);
      Distance_Move(-4, -1);
      while (LiftyMotor.getCurrentPosition() < -515) {
        if (isStopRequested()) {
          break;
        }
        LiftyMotor.setPower(1);
      }
      LiftyMotor.setPower(0);
      IMUTurn(90);
      GrabberL.setPosition(1);
      GrabberR.setPosition(1);
      MecanumYeet(0, 0, 0);
      Distance_MoveStrafe(5, 0.5);
      Distance_Move(28, 1);
    }

    vuforiaSkyStone.close();
  }

  /**
   * Describe this function...
   */
  private void IMUTurn(double TrgtAngle) {
    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    CurrentHeading = angles.firstAngle;
    AngleToTurn = TrgtAngle - CurrentHeading;
    if (AngleToTurn < 0) {
      while (TrgtAngle + 6.5 <= CurrentHeading) {
        if (isStopRequested()) {
          break;
        }
        if (TrgtAngle - CurrentHeading < -40) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, 1);
        } else if (TrgtAngle - CurrentHeading >= -40) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, 0.2);
        }
      }
    } else if (AngleToTurn > 0) {
      while (TrgtAngle - 6.5 >= CurrentHeading) {
        if (isStopRequested()) {
          break;
        }
        if (TrgtAngle - CurrentHeading > 40) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, -1);
        } else if (TrgtAngle - CurrentHeading <= 40) {
          angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
          CurrentHeading = angles.firstAngle;
          MecanumYeet(0, 0, -0.2);
        }
      }
    }
    MecanumYeet(0, 0, 0);
  }

  /**
   * Describe this function...
   */
  private void processTarget() {
    isVisible = true;
    telemetry.addData("X (in)", Double.parseDouble(JavaUtil.formatNumber(displayValue(vuforiaResults.x, "IN"), 2)));
    telemetry.addData("Y (in)", Double.parseDouble(JavaUtil.formatNumber(displayValue(vuforiaResults.y, "IN"), 2)));
    telemetry.addData("Z (in)", Double.parseDouble(JavaUtil.formatNumber(displayValue(vuforiaResults.z, "IN"), 2)));
    telemetry.addData("Rotation about Z (deg)", Double.parseDouble(JavaUtil.formatNumber(vuforiaResults.zAngle, 2)));
    Inaccuracy = Double.parseDouble(JavaUtil.formatNumber(displayValue(vuforiaResults.y, "IN"), 2));
  }

  /**
   * Describe this function...
   */
  private void Initialization() {
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
    motor_drive_bl.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_fl.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    WristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    LiftyMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    LiftyMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    // Initialize Vuforia
    GrabberR.setDirection(Servo.Direction.REVERSE);
    initVuforia();
    // Activate here for camera preview.
    vuforiaSkyStone.activate();
  }

  /**
   * Describe this function...
   */
  private void Distance_Move(double InputValue, double Speed) {
    motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    Constant = 0.65;
    EncoderTicks = (InputValue / (12.85 + Speed * Constant)) * 1440;
    if (Speed < 0) {
      Speed = -Speed;
    }
    if (InputValue > 0) {
      while (motor_drive_fr.getCurrentPosition() >= -EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(-Speed);
        motor_drive_fr.setPower(-Speed);
        motor_drive_bl.setPower(-Speed);
        motor_drive_br.setPower(-Speed);
      }
    } else {
      while (motor_drive_fr.getCurrentPosition() <= EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(-Speed);
        motor_drive_fr.setPower(-Speed);
        motor_drive_bl.setPower(-Speed);
        motor_drive_br.setPower(-Speed);
      }
    }
    if (InputValue < 0) {
      while (motor_drive_fr.getCurrentPosition() <= -EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(Speed);
        motor_drive_fr.setPower(Speed);
        motor_drive_bl.setPower(Speed);
        motor_drive_br.setPower(Speed);
      }
    }
    motor_drive_fl.setPower(0);
    motor_drive_fr.setPower(0);
    motor_drive_bl.setPower(0);
    motor_drive_br.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void Distance_MoveStrafe(double InputValue, double Speed) {
    motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    Constant = 0.65;
    EncoderTicks = (InputValue / (12 + Speed * Constant)) * 1000;
    if (Speed < 0) {
      Speed = -Speed;
    }
    if (InputValue > 0) {
      while (motor_drive_fr.getCurrentPosition() >= -EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(Speed);
        motor_drive_fr.setPower(-Speed);
        motor_drive_bl.setPower(-Speed);
        motor_drive_br.setPower(Speed);
      }
    } else {
      while (motor_drive_fr.getCurrentPosition() <= EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(Speed);
        motor_drive_fr.setPower(-Speed);
        motor_drive_bl.setPower(-Speed);
        motor_drive_br.setPower(Speed);
      }
    }
    if (InputValue < 0) {
      while (motor_drive_fr.getCurrentPosition() <= -EncoderTicks) {
        if (isStopRequested()) {
          break;
        }
        motor_drive_fl.setPower(-Speed);
        motor_drive_fr.setPower(Speed);
        motor_drive_bl.setPower(Speed);
        motor_drive_br.setPower(-Speed);
      }
    }
    motor_drive_fl.setPower(0);
    motor_drive_fr.setPower(0);
    motor_drive_bl.setPower(0);
    motor_drive_br.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void initVuforia() {
    // Rotate phone -90 so back camera faces "forward" direction on robot.
    // Assumes landscape orientation
    vuforiaSkyStone.initialize(
        "", // vuforiaLicenseKey
        VuforiaLocalizer.CameraDirection.BACK, // cameraDirection
        true, // useExtendedTracking
        true, // enableCameraMonitoring
        VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, // cameraMonitorFeedback
        0, // dx
        0, // dy
        0, // dz
        0, // xAngle
        -90, // yAngle
        0, // zAngle
        true); // useCompetitionFieldTargetLocations
  }

  /**
   * Describe this function...
   */
  private boolean isTargetVisible(String trackableName) {
    // Get vuforia results for target.
    vuforiaResults = vuforiaSkyStone.track(trackableName);
    // Is this target visible?
    if (vuforiaResults.isVisible) {
      isVisible = true;
    } else {
      isVisible = false;
    }
    return isVisible;
  }

  /**
   * Describe this function...
   */
  private double displayValue(float originalValue, String units) {
    // Vuforia returns distances in mm.
    if (units.equals("CM")) {
      convertedValue = originalValue / 10;
    } else if (units.equals("M")) {
      convertedValue = originalValue / 1000;
    } else if (units.equals("IN")) {
      convertedValue = originalValue / 25.4;
    } else if (units.equals("FT")) {
      convertedValue = (originalValue / 25.4) / 12;
    } else {
      convertedValue = originalValue;
    }
    return convertedValue;
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
}
