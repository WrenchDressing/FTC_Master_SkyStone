package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumFunction (Blocks to Java)", group = "")
public class MecanumFunction extends LinearOpMode {

  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_bl;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_br;

  
   
    public void MecanumMovement(double YL, double XL, double XR) {
    motor_drive_fl.setPower(YL + (XL + XR));
    motor_drive_bl.setPower(YL - (XL - XR));
    motor_drive_fr.setPower(YL - (XL + XR));
    motor_drive_br.setPower(YL + (XL - XR));
  }
  @Override
  public void runOpMode() {
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");

    // Put initialization blocks here.
    waitForStart();
    
    motor_drive_fl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_fr.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_br.setDirection(DcMotorSimple.Direction.FORWARD);
    
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        MecanumMovement(0, 0, 0);
        // Put loop blocks here.
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */

}
