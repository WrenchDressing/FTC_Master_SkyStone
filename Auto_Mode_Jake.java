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
  
  
}
