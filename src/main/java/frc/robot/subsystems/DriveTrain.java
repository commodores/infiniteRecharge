/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {

  private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(DriveConstants.kLeftMasterPort);
  private final WPI_TalonSRX leftSlave0 = new WPI_TalonSRX(DriveConstants.kLeftSlave0Port);
  private final WPI_TalonSRX leftSlave1 = new WPI_TalonSRX(DriveConstants.kLeftSlave1Port);

  private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(DriveConstants.kRightMasterPort);
  private final WPI_TalonSRX rightSlave0 = new WPI_TalonSRX(DriveConstants.kRightSlave0Port);
  private final WPI_TalonSRX rightSlave1 = new WPI_TalonSRX(DriveConstants.kRightSlave0Port);

  private final PigeonIMU pigeon = new PigeonIMU(rightSlave0);

  private final DifferentialDrive m_drive = new DifferentialDrive(leftMaster, rightMaster);
    
  public DriveTrain() {
      //reset talons
  leftMaster.configFactoryDefault();
  leftSlave0.configFactoryDefault();
  leftSlave0.configFactoryDefault();
  
  rightMaster.configFactoryDefault();
  rightSlave0.configFactoryDefault();
  rightSlave0.configFactoryDefault();
  
  //setup encoders
  leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
  rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

  //zero encoders
  leftMaster.setSelectedSensorPosition(0, 0, 10);
  rightMaster.setSelectedSensorPosition(0, 0, 10); 

  //set talon direction
  leftMaster.setInverted(false);
  leftSlave0.setInverted(false);
  leftSlave1.setInverted(false);

  rightMaster.setInverted(false);
  rightSlave0.setInverted(false);
  rightSlave1.setInverted(false);

  //set encoder direction
  leftMaster.setSensorPhase(false);
  rightMaster.setSensorPhase(true);

  //set slaves to follow
  leftSlave0.follow(leftMaster);
  leftSlave1.follow(leftMaster);

  rightSlave0.follow(rightMaster);
  rightSlave1.follow(rightMaster);
  
  //Coast or Brake
  leftMaster.setNeutralMode(NeutralMode.Brake);
  leftSlave0.setNeutralMode(NeutralMode.Brake);
  leftSlave1.setNeutralMode(NeutralMode.Brake);

  rightMaster.setNeutralMode(NeutralMode.Brake);
  rightSlave0.setNeutralMode(NeutralMode.Brake);
  rightSlave1.setNeutralMode(NeutralMode.Brake);

  m_drive.setSafetyEnabled(false);

  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void curvatureDrive(double speed, double rotation, boolean quickturn){
    m_drive.curvatureDrive(speed, rotation, quickturn);
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    leftMaster.setSelectedSensorPosition(0, 0, 10);
    rightMaster.setSelectedSensorPosition(0, 0, 10);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (getLeftDistance()+getRightDistance()) / 2;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public double getLeftDistance() {
    return leftMaster.getSelectedSensorPosition(0);
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public double getRightDistance() {
    return rightMaster.getSelectedSensorPosition(0);
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public double getYaw() {
    double [] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    return ypr[0];
  }

  public void zeroHeading() {
		pigeon.setYaw(0, 30);
		pigeon.setAccumZAngle(0, 30);
	}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
