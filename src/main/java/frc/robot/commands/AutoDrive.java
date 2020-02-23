/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class AutoDrive extends CommandBase {
  private double distance;
  private double timeOut;

  private double currentHeading = RobotContainer.m_drivetrain.getCurrentAngle();
  /**
   * Creates a new AutoDrive.
   */
  public AutoDrive(double getDistance, double getTimeOut) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
    distance = getDistance;
    timeOut = getTimeOut;

    withTimeout(timeOut);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(distance > 0){
      double pTerm = DriveConstants.kDriveTrainGain * (currentHeading - RobotContainer.m_drivetrain.getCurrentAngle());
      RobotContainer.m_drivetrain.tankDrive(-DriveConstants.kAutoSpeed - pTerm, -DriveConstants.kAutoSpeed + pTerm);
    } else {
      double pTerm = DriveConstants.kDriveTrainGain * (currentHeading + RobotContainer.m_drivetrain.getCurrentAngle());
      RobotContainer.m_drivetrain.tankDrive(DriveConstants.kAutoSpeed + pTerm, DriveConstants.kAutoSpeed - pTerm);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(distance > 0){
      return RobotContainer.m_drivetrain.getAverageDistance() > distance;
    } else {
      return RobotContainer.m_drivetrain.getAverageDistance() < distance;
    }
  }
}