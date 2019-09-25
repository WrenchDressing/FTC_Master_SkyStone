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
          
        int fl = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        int bl = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        int fr = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        int br = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)* slowerMode;
                                      
          //right trigger flips motors
          
        if (gamepad1.right_trigger == 1) {
          motor_drive_fl.setPower(-motor_drive_fl.getPower());
          motor_drive_fr.setPower(-motor_drive_fr.getPower());
          motor_drive_br.setPower(-motor_drive_br.getPower());
          motor_drive_bl.setPower(-motor_drive_bl.getPower());
        }
          
       private void normalizationDrive(int fl, int bl, int fr, int br) {
          if(Math.abs(fl) >= Math.abs(bl) && Math.abs(fl) >= Math.abs(fr) && Math.abs(fl) >= Math.abs(br) && Math.abs(fl) > 1){
            motor_drive_fl.setPower(fl / Math.abs(fl));
            motor_drive_fr.setPower(fr / Math.abs(fl));
            motor_drive_bl.setPower(bl / Math.abs(fl));
            motor_drive_br.setPower(br / Math.abs(fl));
          }
         else if(Math.abs(bl) >= Math.abs(fl) && Math.abs(bl) >= Math.abs(fr) && Math.abs(bl) >= Math.abs(br) && Math.abs(bl) > 1){
            motor_drive_fl.setPower(fl / Math.abs(bl));
            motor_drive_fr.setPower(fr / Math.abs(bl));
            motor_drive_bl.setPower(bl / Math.abs(bl));
            motor_drive_br.setPower(br / Math.abs(bl));
         }
         else if(Math.abs(fr) >= Math.abs(fl) && Math.abs(fr) >= Math.abs(bl) && Math.abs(fr) >= Math.abs(br) && Math.abs(fr) > 1){
            motor_drive_fl.setPower(fl / Math.abs(fr));
            motor_drive_fr.setPower(fr / Math.abs(fr));
            motor_drive_bl.setPower(bl / Math.abs(fr));
            motor_drive_br.setPower(br / Math.abs(fr));
         }
         else if(Math.abs(br) >= Math.abs(bl) && Math.abs(br) >= Math.abs(fr) && Math.abs(br) >= Math.abs(fl) && Math.abs(br) > 1){
            motor_drive_fl.setPower(fl / Math.abs(br));
            motor_drive_fr.setPower(fr / Math.abs(br));
            motor_drive_bl.setPower(bl / Math.abs(br));
            motor_drive_br.setPower(br / Math.abs(br));
         }
       }
        
       private int AbsoluteVal (int num){
         if(num > 0){
           return num;
         }
         else if(num < 0){
           return num * -1;
         }
         else{
           return num;
         }
       }
        
          telemetry.addLine("Tele-Op is Green!");
          telemetry.update();
        
      }  
    }
  } 
