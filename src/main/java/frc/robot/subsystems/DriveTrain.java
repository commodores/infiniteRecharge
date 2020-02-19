package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.DriveManual;

public class DriveTrain extends SubsystemBase {

  CANSparkMax leftMaster = new CANSparkMax(DriveConstants.kLeftMasterPort,MotorType.kBrushless);
  CANSparkMax leftSlave0 = new CANSparkMax(DriveConstants.kLeftSlave0Port,MotorType.kBrushless);

  CANSparkMax rightMaster = new CANSparkMax(DriveConstants.kRightMasterPort,MotorType.kBrushless);
  CANSparkMax rightSlave0 = new CANSparkMax(DriveConstants.kRightSlave0Port,MotorType.kBrushless);
  
  //CANEncoder leftEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4096);
  //CANEncoder rightEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4096);

  //PigeonIMU pigeon = new PigeonIMU(DriveConstants.kPigeonPort);

  DifferentialDrive m_drive = new DifferentialDrive(leftMaster, rightMaster);

  //private double[] yawPitchRoll = new double[3];
    
  public DriveTrain() {

    leftMaster.restoreFactoryDefaults();
    leftSlave0.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave0.restoreFactoryDefaults();

    leftSlave0.follow(leftMaster);
    rightSlave0.follow(rightMaster);

    rightMaster.setOpenLoopRampRate(.5);
    leftMaster.setOpenLoopRampRate(.5);

    m_drive.setSafetyEnabled(false);

    setDefaultCommand(new DriveManual(this));

  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed)
  {
    m_drive.arcadeDrive(moveSpeed,-rotateSpeed);
  }

  public void pathDrive(double leftSpeed, double rightSpeed)
  {
    m_drive.tankDrive(leftSpeed,rightSpeed);
  }

  public void curvatureDrive(double speed, double rotation, boolean quickturn){
    m_drive.curvatureDrive(speed, rotation, quickturn);
  } 


  //public void resetEncoder()
  //{
  //  leftEncoder.setPosition(0);
  //  rightEncoder.setPosition(0);
  //}

  //public CANEncoder getDriveEncoderLeft()
  //{
  //  return leftEncoder;
  //}

  //public CANEncoder getDriveEncoderRight()
  //{
  //  return rightEncoder;
  //}

  //public Rotation2d getIMUHeading()
  //{
  //      pigeon.getYawPitchRoll(yawPitchRoll);
  //      SmartDashboard.putString("YawPitchRoll", "[0]: "+yawPitchRoll[0]+"; [1]: "+yawPitchRoll[1]+"; [2]: "+yawPitchRoll[2]+"; Fused: "+pigeon.getFusedHeading());
  //      return Rotation2d.fromDegrees(yawPitchRoll[0]);
  //}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
