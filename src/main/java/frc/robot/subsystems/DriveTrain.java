package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {

  private final CANSparkMax leftMaster, leftSlave0, rightMaster, rightSlave0;
  
  private final CANEncoder leftEncoder, rightEncoder;

  private final PigeonIMU pigeon;

  //private final SpeedControllerGroup leftMotors, rightMotors;

  //private final DifferentialDrive m_drive;

  private double[] yawPitchRoll = new double[3];

  private DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(DriveConstants.drivetrainWidth);

  private DifferentialDriveOdometry driveOdometry;
    
  public DriveTrain() {

    resetEncoders();

    leftMaster = new CANSparkMax(DriveConstants.kLeftMasterPort,MotorType.kBrushless);
    leftSlave0 = new CANSparkMax(DriveConstants.kLeftSlave0Port,MotorType.kBrushless);

    rightMaster = new CANSparkMax(DriveConstants.kRightMasterPort,MotorType.kBrushless);
    rightSlave0 = new CANSparkMax(DriveConstants.kRightSlave0Port,MotorType.kBrushless);

    leftMaster.restoreFactoryDefaults();
    leftSlave0.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave0.restoreFactoryDefaults();

    leftEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4096);
    rightEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4096);
    
    //set conversion factors
    leftEncoder.setPositionConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);
    leftEncoder.setVelocityConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);

    rightEncoder.setPositionConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);
    rightEncoder.setVelocityConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);

    pigeon = new PigeonIMU(DriveConstants.kPigeonPort);

    // Set current limiting on drve train to prevent brown outs
    Arrays.asList(leftMaster, leftSlave0, rightMaster, rightSlave0)
          .forEach((CANSparkMax spark) -> spark.setSmartCurrentLimit(40));

    // Set motors to brake when idle. We don't want the drive train to coast.
    Arrays.asList(leftMaster, leftSlave0, rightMaster, rightSlave0)
          .forEach((CANSparkMax spark) -> spark.setIdleMode(IdleMode.kBrake));

    //determine real numbers to use here
    leftMaster.setOpenLoopRampRate(0.2);
    leftSlave0.setOpenLoopRampRate(0.2);
    rightMaster.setOpenLoopRampRate(0.2);
    rightSlave0.setOpenLoopRampRate(0.2);

    leftSlave0.follow(leftMaster);
    rightSlave0.follow(rightMaster);

    //initialize odometry
    driveOdometry = new DifferentialDriveOdometry(getIMUHeading());

    //leftMotors = new SpeedControllerGroup(leftMaster, leftSlave0);
    //rightMotors = new SpeedControllerGroup(rightMaster, rightSlave0);

    //m_drive = new DifferentialDrive(leftMotors, rightMotors);
    
    //m_drive.setSafetyEnabled(false);

  }

  //public void curvatureDrive(double speed, double rotation, boolean quickturn){
  //  m_drive.curvatureDrive(speed, rotation, quickturn);
  //}

  /* Drive methods*/

  //use kinematics to calculate wheel speeds
  public void arcadeDrive(double speed, double turn) {
    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(speed, 0, turn);

    //calculate wheel speeds
    DifferentialDriveWheelSpeeds wheelSpeeds = driveKinematics.toWheelSpeeds(chassisSpeeds);
    //keep output between -1 and 1
    wheelSpeeds.normalize(1);

    //output speeds
    leftMaster.set(wheelSpeeds.leftMetersPerSecond);
    rightMaster.set(-wheelSpeeds.rightMetersPerSecond);    
  }

  //control wheels directly through voltage supply
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMaster.setVoltage(leftVolts);
    rightMaster.setVoltage(-rightVolts);
  }

  //control wheels through meters per second
  public void tankDriveMetersPerSecond(double leftSpeed, double rightSpeed) {
    DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(leftSpeed, rightSpeed);
    wheelSpeeds.normalize(1);

    leftMaster.set(wheelSpeeds.leftMetersPerSecond);
    rightMaster.set(-wheelSpeeds.rightMetersPerSecond); 
  }

  public DifferentialDriveKinematics getKinematics() {
    return driveKinematics;
  }

  /** Odometry methods */

  //get position coordinates of robot
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  //Get the individual wheel speeds for the robot
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftSpeed(), getRightSpeed());
  }

  //reset the position and heading for odometry
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    driveOdometry.resetPosition(pose, getIMUHeading());
  }


  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (getLeftDistance()+getRightDistance()) / 2;
  }

  public double getLeftDistance() {
    return leftEncoder.getPosition();
  }

  public double getRightDistance() {
    return rightEncoder.getPosition();
  }

  //get average speed between left and right encoders in meters per second
  public double getAverageSpeed() {
    return (leftEncoder.getVelocity() + rightEncoder.getVelocity())/ 2;
  }

  //get left speed in meters per second
  public double getLeftSpeed() {
    return leftEncoder.getVelocity();
  }

  //get right speed in meters per second
  public double getRightSpeed() {
    return rightEncoder.getVelocity();
  }

  public Rotation2d getIMUHeading()
    {
        pigeon.getYawPitchRoll(yawPitchRoll);
        SmartDashboard.putString("YawPitchRoll", "[0]: "+yawPitchRoll[0]+"; [1]: "+yawPitchRoll[1]+"; [2]: "+yawPitchRoll[2]+"; Fused: "+pigeon.getFusedHeading());
        return Rotation2d.fromDegrees(yawPitchRoll[0]);
    }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
