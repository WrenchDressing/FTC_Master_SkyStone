package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;            
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Teleop3 (Blocks to Java)", group = "")
public class Teleop3 extends LinearOpMode {

  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_bl;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_br;
    /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */

  public void runOpMode() {
    
    double slowerMode;
    
    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");
    motor_alt_pull = hardwareMap.dcMotor.get("motor_alt_pull");
    motor_drive_br.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    
    waitForStart();
    slowerMode = 1.0;
      
      while (opModeIsActive()) {
          
          //left trigger slows motors
          
        if (gamepad1.left_trigger >= 0.5) {
          slowerMode = .25;
        } else {
            slowerMode = 1.0;
        }
        
          //motor gamestick algorithm
          
        motor_drive_fl.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode);
        motor_drive_bl.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode);
        motor_drive_fr.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode);
        motor_drive_br.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)* slowerMode);
                                      
          //right trigger flips motors
          
        if (gamepad1.right_trigger == 1) {
          motor_drive_fl.setPower(-motor_drive_fl.getPower());
          motor_drive_fr.setPower(-motor_drive_fr.getPower());
          motor_drive_br.setPower(-motor_drive_br.getPower());
          motor_drive_bl.setPower(-motor_drive_bl.getPower());
        }
          
        /*  //gamepad x and b runs collections servos
          
        if (gamepad2.x == true) {
          collection_servo.setPower(-1);
          collection_servo2.setPower(1);
        } else if (gamepad2.b == true) {
          collection_servo.setPower(1);
          collection_servo2.setPower(-1);
          }
            else {
              collection_servo2.setPower(.055);
              collection_servo.setPower(.055);
            }
            
          //hook mechanism code using bumpers

        if (gamepad2.left_bumper == false && gamepad2.right_bumper == false) {
          motor_alt_pull.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          motor_alt_pull.setTargetPosition(Current_Val3);
          motor_alt_pull.setPower(1); 
        } else {
          motor_alt_pull.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
          if (gamepad2.left_bumper == true){
            motor_alt_pull.setPower(.25);
          } else if (gamepad2.right_bumper == true) {
              motor_alt_pull.setPower(-.25);
            }
            Current_Val3 = motor_alt_pull.getCurrentPosition();
        }

          //gamepad 2 a and y for running elbow code, inlcuding l3 and r3 for auto positions

        if (gamepad2.a == false && gamepad2.y == false) {
          arm_motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          if (cancel1) {
          Current_Val2 = arm_motor2.getCurrentPosition();
          }
          if (gamepad2.left_stick_button == true) {
            arm_motor2.setTargetPosition(-25);
            arm_motor2.setPower(0.25);
            cancel1 = true;
          }
          else if (gamepad2.right_stick_button == true) {
            arm_motor2.setTargetPosition(875);
            arm_motor2.setPower(0.15);
            cancel1 = true;
          }
          else {
          arm_motor2.setTargetPosition(Current_Val2);
          cancel1 = false;
          arm_motor2.setPower(1);
          }
        } 
          else {
            arm_motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            cancel1 = true;
            if (gamepad2.a == true){
              arm_motor2.setPower(-.10);
            } else if (gamepad2.y == true) {
              arm_motor2.setPower(.25);
              }
            }
            
            //gamepad 2 dpad up and down for running arm code, inlcuding l3 and r3 auto positions
            
        if (gamepad2.dpad_down == false && gamepad2.dpad_up == false) {
          arm_motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
          if (cancel2) {
          Current_Val = arm_motor1.getCurrentPosition();
          }
          if (gamepad2.left_stick_button == true) {
            arm_motor1.setTargetPosition(-1500);
            arm_motor1.setPower(0.20);
            cancel2 = true;
          }
          else if (gamepad2.right_stick_button == true) {
            arm_motor1.setTargetPosition(-650);
            arm_motor1.setPower(0.10);
            cancel2 = true;
          }
          else {
          arm_motor1.setTargetPosition(Current_Val);
          cancel2 = false;
          arm_motor1.setPower(1);
          }
        } else {
          arm_motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
          cancel2 = true;
          if (gamepad2.dpad_down == true) {
            arm_motor1.setPower(0.10);
          } else if (gamepad2.dpad_up == true) {
            arm_motor1.setPower(-0.15);
          }
        } */
        
          telemetry.addLine("Tele-Op is Green!");
          //telemetry.addData("Target Arm Position", Current_Val);
          //telemetry.addData("Target Elbow Position", Current_Val2);
          telemetry.update();
        
      }  
    }
  }













