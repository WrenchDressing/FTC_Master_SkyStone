import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class FIRSTteleopmode extends LinearOpMode {

    //numbers after underscores indicate ports and orientation (i.e. fl= front left)
    private Gyroscope imu;
    private DcMotor motor_drive_fl;
    private DcMotor motor_drive_fr;
    private DcMotor motor_drive_bl;
    private DcMotor motor_drive_br;
    private DigitalChannel digitalTouch_01;
    private DistanceSensor sensorColorRange_0;
    //private Servo servoTest_0;

    @Override
    public void runOpMode() {

        imu = hardwareMap.get(Gyroscope.class, "imu");
        motor_drive_fl = hardwareMap.get(DcMotor.class, "motor_drive_fl");
        motor_drive_fr = hardwareMap.get(DcMotor.class, "motor_drive_fr");
        motor_drive_bl = hardwareMap.get(DcMotor.class, "motor_drive_bl");
        motor_drive_br = hardwareMap.get(DcMotor.class, "motor_drive_br");
        motor_drive_fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_drive_fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_drive_bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_drive_br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_drive_fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor_drive_fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor_drive_bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor_drive_br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        digitalTouch_01 = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange_0 = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest_0 = hardwareMap.get(Servo.class, "servoTest_0");
        // set digital channel to input mode.
        digitalTouch_01.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double tgtpowerfl;
        double tgtpowerfr;
        double tgtpowerbl;
        double tgtpowerbr;
        double multiplier = 1.0;


        while (opModeIsActive()) {
            tgtpowerfl = (-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x);
            tgtpowerfr = (gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x);
            tgtpowerbl = (-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x);
            tgtpowerbr = (gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x);

            if(!gamepad1.left_bumper) {

            }
            else {
                tgtpowerfl = tgtpowerfl / 4;
                tgtpowerfr = tgtpowerfr / 4;
                tgtpowerbl = tgtpowerbl / 4;
                tgtpowerbr = tgtpowerbr / 4;
            }

            motor_drive_fl.setPower(tgtpowerfl);
            motor_drive_fr.setPower(tgtpowerfr);
            motor_drive_bl.setPower(tgtpowerbl);
            motor_drive_br.setPower(tgtpowerbr);


            //telemetry.addData("Servo Position", servoTest_0.getPosition());
            telemetry.addData("Target Power Front Left", tgtpowerfl);
            telemetry.addData("Target Power Front Right", tgtpowerfr);
            telemetry.addData("Target Power Back Right", tgtpowerbl);
            telemetry.addData("Target Power Back Right", tgtpowerbr);
            telemetry.addData("Distance (cm)", sensorColorRange_0.getDistance(DistanceUnit.CM));
            // is button pressed?
            if (!digitalTouch_01.getState()) {
                // button is pressed.
                telemetry.addData("Button", "PRESSED");
            } else {
                // button is not pressed.
                telemetry.addData("Button", "NOT PRESSED");
            }


            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
