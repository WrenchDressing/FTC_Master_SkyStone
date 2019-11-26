package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;            
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "JakesTeleOp", group = "")
public class JakesTeleOp extends LinearOpMode {

  private DcMotor motor_drive_fl;
  private DcMotor motor_drive_bl;
  private DcMotor motor_drive_fr;
  private DcMotor motor_drive_br;
  private DcMotor LiftyMotor;
  private DcMotor WristMotor;
  public double fl_pow;
  public double fr_pow;
  public double bl_pow;
  public double br_pow;
  public double slowerMode;
  public double[10] smoothArrayLX;
  public double[10] smoothArrayLY;
  public double[10] smoothArrayRX;
  private Servo GrabberL;
  private Servo GrabberR;
  private Servo FoundationServoL;
  private Servo FoundationServoR;
    /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */

  public void runOpMode() {
    
        double GrabberVariable;


    motor_drive_fl = hardwareMap.dcMotor.get("motor_drive_fl");
    motor_drive_bl = hardwareMap.dcMotor.get("motor_drive_bl");
    motor_drive_fr = hardwareMap.dcMotor.get("motor_drive_fr");
    motor_drive_br = hardwareMap.dcMotor.get("motor_drive_br");
    LiftyMotor = hardwareMap.dcMotor.get("LiftyMotor");
    WristMotor = hardwareMap.dcMotor.get("WristMotor");
      GrabberL = hardwareMap.servo.get("GrabberL");
    GrabberR = hardwareMap.servo.get("GrabberR");
    motor_drive_br.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setDirection(DcMotorSimple.Direction.REVERSE);
    motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    LiftyMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    WristMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    LiftyMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    WristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    GrabberR.setDirection(Servo.Direction.REVERSE);
    FoundationServoL = hardwareMap.servo.get("FoundationServoL");
    FoundationServoR = hardwareMap.servo.get("FoundationServoR");
    //FoundationServoR.setDirection(Servo.Direction.REVERSE);
 LiftyMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
 LiftyMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    waitForStart();
    slowerMode = 1.0;
    
     
      while (opModeIsActive()) {

  telemetry.addData("LiftyMotorCurrentPosition", LiftyMotor.getCurrentPosition());
   telemetry.update();
           GrabberVariable = 1;
      // Put run blocks here.
if (gamepad2.left_bumper) {
    FoundationServoL.setPosition(0);
    FoundationServoR.setPosition(1);
    
}
else {
    FoundationServoL.setPosition(1);
    FoundationServoR.setPosition(0);
    
}
if (gamepad2.x){
  WristMotor.setPower(.15);
  
}
else if (gamepad2.b){
  WristMotor.setPower(-.15);
}
else {
  WristMotor.setPower(0);
}

if (gamepad2.dpad_up && LiftyMotor.getCurrentPosition() > -11000) {
    LiftyMotor.setPower(-1);
} else if (gamepad2.dpad_down && LiftyMotor.getCurrentPosition()  < -150) {
    LiftyMotor.setPower(1);
} else {
    LiftyMotor.setPower(0);
    }


 
        if (gamepad2.a) {
          GrabberR.setPosition(0.55);
          GrabberL.setPosition(0.5);
        }
        else {
            GrabberR.setPosition(1);
            GrabberL.setPosition(1);
        }
     

    
  
        if (gamepad1.left_trigger >= 0.5) {
          slowerMode = 0.10;
        } else {
            slowerMode = 1.0;
        }
        
        
          //motor gamestick algorithm
          
        //double fl_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        //double bl_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        //double fr_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        //double br_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        
        //insert drive mode(standard or normalization)
        
        if (gamepad1.a) {
            smoothing();
        } else if (gamepad1.x) {
            newSmoothing();
        } else {
            fl_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
            bl_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
            fr_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
            br_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        }
        
        normalizationDrive(fl_pow, fr_pow, bl_pow, br_pow);
        
                                      
          //right trigger flips motors
          
        if (gamepad1.right_trigger == 1) {
          motor_drive_fl.setPower(-motor_drive_fl.getPower());
          motor_drive_fr.setPower(-motor_drive_fr.getPower());
          motor_drive_br.setPower(-motor_drive_br.getPower());
          motor_drive_bl.setPower(-motor_drive_bl.getPower());
        }
        
          //motor powering
          
        motor_drive_fl.setPower(fl_pow);
        motor_drive_fr.setPower(fr_pow);
        motor_drive_bl.setPower(bl_pow);
        motor_drive_br.setPower(br_pow);
        
        //if (gamepad1.x){
          //servo_Kaleb.setPosition(0.9);
     //   }

        
          telemetry.addLine("Tele-Op is Green!");
          telemetry.addData("FL", motor_drive_fl.getPower());
          telemetry.addData("FR", motor_drive_fr.getPower());
          telemetry.addData("BL", motor_drive_bl.getPower());
          telemetry.addData("BR", motor_drive_br.getPower());
          telemetry.update();
        
      }  
    }
    
    //standard drive algorithm
  
    private void standardDrive(){
      
        fl_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        bl_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        fr_pow = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * slowerMode;
        br_pow = (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * slowerMode;
        
    }
    
    //normalized standard drive algorithm
  
    private void normalizationDrive(double fl, double fr, double bl, double br) {
          if(Math.abs(fl) >= Math.abs(bl) && Math.abs(fl) >= Math.abs(fr) && Math.abs(fl) >= Math.abs(br) && Math.abs(fl) > 1){
            fl_pow = (fl / Math.abs(fl));
            fr_pow = (fr / Math.abs(fl));
            bl_pow = (bl / Math.abs(fl));
            br_pow = (br / Math.abs(fl));
          } 
         else if(Math.abs(bl) >= Math.abs(fl) && Math.abs(bl) >= Math.abs(fr) && Math.abs(bl) >= Math.abs(br) && Math.abs(bl) > 1){
            fl_pow = (fl / Math.abs(bl));
            fr_pow = (fr / Math.abs(bl));
            bl_pow = (bl / Math.abs(bl));
            br_pow = (br / Math.abs(bl));
         }
         else if(Math.abs(fr) >= Math.abs(fl) && Math.abs(fr) >= Math.abs(bl) && Math.abs(fr) >= Math.abs(br) && Math.abs(fr) > 1){
            fl_pow = (fl / Math.abs(fr));
            fr_pow = (fr / Math.abs(fr));
            bl_pow = (bl / Math.abs(fr));
            br_pow = (br / Math.abs(fr));
         }
         else if(Math.abs(br) >= Math.abs(bl) && Math.abs(br) >= Math.abs(fr) && Math.abs(br) >= Math.abs(fl) && Math.abs(br) > 1){
            fl_pow = (fl / Math.abs(br));
            fr_pow = (fr / Math.abs(br));
            bl_pow = (bl / Math.abs(br));
            br_pow = (br / Math.abs(br));
         }
       }
       
      //smoothed out driving algorithm (finer movement becomes finer, faster movement comes faster)
  
      private void smoothing(){
        double sLX = gamepad1.left_stick_x;
        double sLY = gamepad1.left_stick_y;
        double sRX = gamepad1.right_stick_x;
        
        if(Math.abs(gamepad1.left_stick_x) < 0.33){
            sLX = (sLX / 2);
          } 
         else if(Math.abs(gamepad1.left_stick_x) >= 0.33 && Math.abs(gamepad1.left_stick_x) < 0.67){
           if(gamepad1.left_stick_x > 0){
             sLX = (sLX - 0.16);
           }
           else{
             sLX = (sLX + 0.16);
           }
         }
         else if(Math.abs(gamepad1.left_stick_x) >= 0.67){
           if(gamepad1.left_stick_x > 0){
            sLX = (sLX * (3/2) - 0.50);
           }
           else{
            sLX = (sLX * (3/2) + 0.50);
           }
         }
         
         if(Math.abs(gamepad1.left_stick_y) < 0.33){
            sLY = (sLY / 2);
          } 
         else if(Math.abs(gamepad1.left_stick_y) >= 0.33 && Math.abs(gamepad1.left_stick_y) < 0.67){
           if(gamepad1.left_stick_y > 0){
             sLY = (sLY - 0.16);
           }
           else{
             sLY = (sLY + 0.16);
           }
         }
         else if(Math.abs(gamepad1.left_stick_y) >= 0.67){
           if(gamepad1.left_stick_y > 0){
            sLY = (sLY * (3/2) - 0.50);
           }
           else{
            sLY = (sLY * (3/2) + 0.50);
           }
         }
         
         if(Math.abs(gamepad1.right_stick_x) < 0.33){
            sRX = (sRX / 2);
          } 
         else if(Math.abs(gamepad1.right_stick_x) >= 0.33 && Math.abs(gamepad1.right_stick_x) < 0.67){
           if(gamepad1.right_stick_x > 0){
             sRX = (sRX - 0.16);
           }
           else{
             sRX = (sRX + 0.16);
           }
         }
         else if(Math.abs(gamepad1.right_stick_x) >= 0.67){
           if(gamepad1.right_stick_x > 0){
            sRX = (sRX * (3/2) - 0.50);
           }
           else{
            sRX = (sRX * (3/2) + 0.50);
           }
         }
         
         fl_pow = (sLY - sLX - sRX) * slowerMode;
         bl_pow = (-sLY - sLX + sRX) * slowerMode;
         fr_pow = (-sLY - sLX - sRX) * slowerMode;
         br_pow = (sLY - sLX + sRX) * slowerMode;
         
      }
      
      private void newSmoothing(){
        private double totalLX = 0;
        private double totalLY = 0;
        private double totalRX = 0;
        for(int i = 8; i >= 0; i--){
          totalLX = totalLX + smoothArrayLX[i];
          smoothArrayLX[i] = smoothArrayLX[i+1];
        }
        for(int i = 8; i >= 0; i--){
          totalRX = totalRX + smoothArrayRX[i];
          smoothArrayRX[i] = smoothArrayRX[i+1];
        }
        for(int i = 8; i >= 0; i--){
          totalLY = totalLY + smoothArrayLY[i];
          smoothArrayLY[i] = smoothArrayLY[i+1];
        }
        smoothArrayLX[0] = gamepad1.left_stick_x;
        smoothArrayLY[0] = gamepad1.left_stick_y;
        smoothArrayRX[0] = gamepad1.right_stick_x;
        totalLX = (totalLX + gamepad1.left_stick_x) / 10;
        totalLY = (totalLY + gamepad1.left_stick_y) / 10;
        totalRX = (totalRX + gamepad1.right_stick_x) / 10;
        
        fl_pow = (totalLY - totalLX - totalRX) * slowerMode;
        bl_pow = (-totalLY - totalLX + totalRX) * slowerMode;
        fr_pow = (-totalLY - totalLX - totalRX) * slowerMode;
        br_pow = (totalLY - totalLX + totalRX) * slowerMode;
        
      } 
