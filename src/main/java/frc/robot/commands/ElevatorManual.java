/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LeftElevator;

public class ElevatorManual extends CommandBase {
  private final LeftElevator m_leftElevator;
  /**
   * Creates a new ElevatorManual.
 * @param leftElevator
   */
  public ElevatorManual(LeftElevator leftElevator) {
    this.m_leftElevator = leftElevator;
    addRequirements(m_leftElevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.leftJoystick.getRawAxis(1) > 0.5 ){
      m_leftElevator.ElevatorUp();
    } else if(RobotContainer.leftJoystick.getRawAxis(1) < -0.5 ) {
      m_leftElevator.ElevatorDown();
    }else {
      m_leftElevator.StopElevator();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
