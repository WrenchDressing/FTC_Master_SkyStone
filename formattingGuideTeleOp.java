package org.firstinspires.ftc.teamcode;
//importing DON'T TOUCH
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;            
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//change the name below to the name of your class
@TeleOp(name = "Whatever your name is", group = "")
public class Teleop3 extends LinearOpMode {

  //initialize motors, servos and sensors here
  private DcMotor motor_drive;
  private CRServo collection_servo;
  
    /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */

  public void runOpMode() {
  
    //initialize motors, servos, and sensors to hardware here
    
    motor_drive = hardwareMap.dcMotor.get("motor_drive");
    collection_servo = hardwareMap.crservo.get("collection_servo");

    //set direction of motors (right are reversed) and encoder behavior here
    
    //motor_drive.setDirection(DcMotorSimple.Direction.REVERSE);
    
    motor_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    //initialize and declare other values here, like this
    //int power = 1;
    double slowerMode;
    
    waitForStart();
      
      while (opModeIsActive()) {
      
        //set motors algorithms here
        
        motor_drive.setPower(-gamepad1.left_stick_y * slowerMode);
        
      //set other algorithms/methods here
        
        //left trigger slows motors
        
        if (gamepad1.left_trigger >= 0.5) {
          slowerMode = .25;
        } else {
            slowerMode = 1.0;
          }
          
          //right trigger flips motors
          
        if (gamepad1.right_trigger == 1) {
          motor_drive.setPower(-motor_drive.getPower());
          
        }
          
          //gamepad x and b runs servo
          
        if (gamepad2.x == true) {
          collection_servo.setPower(-1);
        } else if (gamepad2.b == true) {
          collection_servo.setPower(1);
          }
            else {
              collection_servo.setPower(.055);
            }
            
          //telemetry code... THIS WILL SHOW UP ON YOUR PHONE. You can also make it show changing values!
        
          telemetry.addLine("Tele-Op is Green!");
          telemetry.addData("Motor Power", motor_drive.getPower());
          telemetry.update();
        
      }  
    }
  }
