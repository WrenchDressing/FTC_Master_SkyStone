package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;            
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Auto_Mode_Jake", group="")
@Disabled
public class Auto_Mode_Jake extends LinearOpMode {
  
  
  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_bl;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_br;
  private Servo servo_Kaleb;
  public double fl_pow;
  public double fr_pow;
  public double bl_pow;
  public double br_pow;
  public double slowerMode;
  
  public void runOpMode() {
    
    
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");
    servo_Kaleb = hardwareMap.servo.get("servo_Kaleb");
    motor_drive_br.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    
    waitForStart();
  
}
