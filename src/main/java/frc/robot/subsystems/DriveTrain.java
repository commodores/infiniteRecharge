package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.DriveManual;

public class DriveTrain extends SubsystemBase {

  CANSparkMax leftMaster = new CANSparkMax(DriveConstants.kLeftMasterPort,MotorType.kBrushless);
  CANSparkMax leftSlave0 = new CANSparkMax(DriveConstants.kLeftSlave0Port,MotorType.kBrushless);

  CANSparkMax rightMaster = new CANSparkMax(DriveConstants.kRightMasterPort,MotorType.kBrushless);
  CANSparkMax rightSlave0 = new CANSparkMax(DriveConstants.kRightSlave0Port,MotorType.kBrushless);
  
  CANEncoder leftEncoder = leftMaster.getEncoder();
  CANEncoder rightEncoder = rightMaster.getEncoder();

  PigeonIMU pigeon = new PigeonIMU(DriveConstants.kPigeonPort);

  DifferentialDrive m_drive = new DifferentialDrive(leftMaster, rightMaster);
  
  private double[] yawPitchRoll = new double[3];
  private double[] xyz_dps = new double[3];
  PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
	PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();

  private final DifferentialDriveOdometry m_odometry;
    
  public DriveTrain() {

    leftMaster.restoreFactoryDefaults();
    leftSlave0.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave0.restoreFactoryDefaults();

    leftSlave0.follow(leftMaster);
    rightSlave0.follow(rightMaster);

    rightMaster.setOpenLoopRampRate(1.0);
    leftMaster.setOpenLoopRampRate(1.0);

    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    rightMaster.setIdleMode(IdleMode.kBrake);
    leftMaster.setIdleMode(IdleMode.kBrake);

    leftMaster.setInverted(true);
    m_drive.setRightSideInverted(false);
    
    m_drive.setSafetyEnabled(false);

    resetEncoders();

    leftEncoder.setPositionConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);
    leftEncoder.setVelocityConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);

    rightEncoder.setPositionConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);
    rightEncoder.setVelocityConversionFactor(DriveConstants.drivetrainEncoderConversionFactor);

    m_odometry = new DifferentialDriveOdometry(getIMUHeading());

    setDefaultCommand(new DriveManual(this));

  }

  @Override
  public void periodic() {
    m_odometry.update(getIMUHeading(), getLeftEncoder(), getRightEncoder());
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed)
  {
    m_drive.arcadeDrive(moveSpeed,-rotateSpeed);
  }

  public void tankDrive(double leftSpeed, double rightSpeed)
  {
    m_drive.tankDrive(leftSpeed,rightSpeed);
  }

  public void curvatureDrive(double speed, double rotation, boolean quickturn){
    m_drive.curvatureDrive(speed, rotation, quickturn);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMaster.setVoltage(leftVolts);
    rightMaster.setVoltage(-rightVolts);
    m_drive.feed();
  }


  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftSpeed(), getRightSpeed());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, getIMUHeading());
  }

  public void resetEncoders()
  {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public double getLeftEncoder() {
    return leftEncoder.getPosition();
  }

  public double getRightEncoder() {
    return rightEncoder.getPosition();
  }

  public double getAverageDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition())/ 2;
  }

  public double getAverageSpeed() {
    return (leftEncoder.getVelocity() + rightEncoder.getVelocity())/ 2;
  }

  public double getLeftSpeed() {
    return leftEncoder.getVelocity();
  }

  public double getRightSpeed() {
    return rightEncoder.getVelocity();
  }

  public void setMaxOutput(double maxOutput){
    m_drive.setMaxOutput(maxOutput);
  }
  
  public Rotation2d getIMUHeading() {
        pigeon.getYawPitchRoll(yawPitchRoll);
        SmartDashboard.putString("YawPitchRoll", "[0]: "+yawPitchRoll[0]+"; [1]: "+yawPitchRoll[1]+"; [2]: "+yawPitchRoll[2]+"; Fused: "+pigeon.getFusedHeading());
        return Rotation2d.fromDegrees(yawPitchRoll[0]);
  }

  public double getTurnRate() {
    pigeon.getRawGyro(xyz_dps);
    double angleRate = xyz_dps[2];
    return angleRate;
  }

  public double getCurrentAngle() {
    pigeon.getFusedHeading(fusionStatus);
    return fusionStatus.heading;
  }
  
}
