package frc.robot.subsystems;

import java.util.Arrays;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.SPI;

public class DriveTrain extends SubsystemBase {

  private final CANSparkMax leftMaster, leftSlave0, rightMaster, rightSlave0;
  
  private final CANEncoder leftEncoder, rightEncoder;

  private final AHRS navX;

  private final DifferentialDrive m_drive;
    
  public DriveTrain() {
    leftMaster = new CANSparkMax(DriveConstants.kLeftMasterPort,MotorType.kBrushless);
    leftSlave0 = new CANSparkMax(DriveConstants.kLeftSlave0Port,MotorType.kBrushless);

    rightMaster = new CANSparkMax(DriveConstants.kRightMasterPort,MotorType.kBrushless);
    rightSlave0 = new CANSparkMax(DriveConstants.kRightSlave0Port,MotorType.kBrushless);

    leftEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4096);
    rightEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4096);

    navX = new AHRS(SPI.Port.kMXP);

    leftMaster.restoreFactoryDefaults();
    leftSlave0.restoreFactoryDefaults();
    rightMaster.restoreFactoryDefaults();
    rightSlave0.restoreFactoryDefaults();

    leftSlave0.follow(leftMaster);
    rightSlave0.follow(rightMaster);

    m_drive = new DifferentialDrive(leftMaster, rightMaster);
    
    m_drive.setSafetyEnabled(false);

    // Set current limiting on drve train to prevent brown outs
    Arrays.asList(leftMaster, leftSlave0, rightMaster, rightSlave0)
          .forEach((CANSparkMax spark) -> spark.setSmartCurrentLimit(40));

    // Set motors to brake when idle. We don't want the drive train to coast.
    Arrays.asList(leftMaster, leftSlave0, rightMaster, rightSlave0)
          .forEach((CANSparkMax spark) -> spark.setIdleMode(IdleMode.kBrake));

    //determine real numbers to use here
    leftMaster.setOpenLoopRampRate(0.5);
    rightMaster.setOpenLoopRampRate(0.5);
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void curvatureDrive(double speed, double rotation, boolean quickturn){
    m_drive.curvatureDrive(speed, rotation, quickturn);
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

  public double getAngle(){
		return -navX.getAngle();
	}

	public double getYaw() {
		return navX.getYaw();
	}

	public double getRoll(){
		return navX.getRoll();
	}

	public double getPitch(){
		return navX.getPitch();
	}

	public void zeroHeading() {
		navX.reset();
	}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
