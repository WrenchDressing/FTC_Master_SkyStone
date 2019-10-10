package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "DistanceMovementTest (Blocks to Java)", group = "")
public class DistanceMovementTest extends LinearOpMode {

  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_bl;
  private DcMotor motor_drive_br;

  /**
   * Describe this function...
   */
   /*
   public void MecanumMovement(double YL, double XL, double XR) {

    motor_drive_fl.setPower(0);
    motor_drive_bl.setPower(0);
    motor_drive_fr.setPower(0);
    motor_drive_br.setPower(0);
  }
   */
  private void Distance_Move(double InputValue, double Speed) {
    double EncoderTicks;
    double Constant;

    motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    Constant = 0.65;
    EncoderTicks = (InputValue / (12.85 + Speed * Constant)) * 1440;
    
    

    if (Speed < 0){
      Speed = -Speed;
    }
    if (InputValue > 0){
      
      while (!(motor_drive_fr.getCurrentPosition() >= EncoderTicks)){
         //Distance_Move(1, 0, 0); 
     motor_drive_fr.setPower(Speed);
      motor_drive_br.setPower(Speed);
      motor_drive_fl.setPower(Speed);
      motor_drive_bl.setPower(Speed);
      } 
    }
    else {
      while (!(motor_drive_fr.getCurrentPosition() <= EncoderTicks)) {
       //Distance_Move(0, 0, 0);

      motor_drive_fr.setPower(-Speed);
      motor_drive_br.setPower(-Speed);
      motor_drive_fl.setPower(-Speed);
      motor_drive_bl.setPower(-Speed);
      }
    }
      
    
  if (InputValue > 0){
      motor_drive_fl.setPower(Speed);
      motor_drive_bl.setPower(Speed);
      motor_drive_fr.setPower(Speed);
      motor_drive_br.setPower(Speed);
    }
    else {
      motor_drive_fl.setPower(-Speed);
      motor_drive_bl.setPower(-Speed);
      motor_drive_fr.setPower(-Speed);
      motor_drive_br.setPower(-Speed);
    }
    
   
  }
  
  


  
   // This function is executed when this Op Mode is selected from the Driver Station.
   
  @Override
    public void runOpMode(){
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");


    motor_drive_fl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_fr.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_br.setDirection(DcMotorSimple.Direction.FORWARD);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    motor_drive_bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    waitForStart();
    Distance_Move(10, 0.5);
    
    /*while (opModeIsActive()) {
      motor_drive_fl.setPower(0.5);
      motor_drive_fr.setPower(0.5);
      motor_drive_bl.setPower(0.5);
      motor_drive_br.setPower(0.5);
    }*/
  }
}


