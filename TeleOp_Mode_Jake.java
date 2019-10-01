package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;            
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "JakeTeleOp", group = "")
public class JakeTeleOp extends LinearOpMode {

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
          slowerMode = 0.25;
        } else {
            slowerMode = 1.0;
        }
        
          //motor gamestick algorithm
          
        double fl = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        double bl = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        double fr = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        double br = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        
        smoothing(fl,bl,fr,br);
                                      
          //right trigger flips motors
          
        if (gamepad1.right_trigger == 1) {
          motor_drive_fl.setPower(-motor_drive_fl.getPower());
          motor_drive_fr.setPower(-motor_drive_fr.getPower());
          motor_drive_br.setPower(-motor_drive_br.getPower());
          motor_drive_bl.setPower(-motor_drive_bl.getPower());
        }

        
          telemetry.addLine("Tele-Op is Green!");
          telemetry.addData("FL", motor_drive_fl.getPower());
          telemetry.addData("FR", motor_drive_fr.getPower());
          telemetry.addData("BL", motor_drive_bl.getPower());
          telemetry.addData("BR", motor_drive_br.getPower());
          telemetry.update();
        
      }  
    }
    
    private void normalizationDrive(double fl, double fr, double bl, double br) {
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
         else{
            motor_drive_fl.setPower(fl);
            motor_drive_fr.setPower(fr);
            motor_drive_bl.setPower(bl);
            motor_drive_br.setPower(br);
           
         }
       }
       
      private void smoothing(double fl, double fr, double bl, double br){
      if(Math.abs(gamepad1.left_stick_x) < 0.33){
            motor_drive_fl.setPower(fl / 2);
            motor_drive_fr.setPower(fr / 2);
            motor_drive_bl.setPower(bl / 2);
            motor_drive_br.setPower(br / 2);
          } 
         else if(Math.abs(gamepad1.left_stick_x) >= 0.33 && Math.abs(gamepad1.left_stick_x) < 0.67){
            motor_drive_fl.setPower(fl - 0.16);
            motor_drive_fr.setPower(fr - 0.16);
            motor_drive_bl.setPower(bl - 0.16);
            motor_drive_br.setPower(br - 0.16);
         }
         else if(Math.abs(gamepad1.left_stick_x) >= 0.67){
            motor_drive_fl.setPower(fl * (3/2) - 0.16);
            motor_drive_fr.setPower(fr * (3/2) - 0.16);
            motor_drive_bl.setPower(bl * (3/2) - 0.16);
            motor_drive_br.setPower(br * (3/2) - 0.16);
         }
    
    }
  } 
