/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RightElevator;

public class ElevatorManualTwo extends CommandBase {
  private final RightElevator m_rightElevator;
  /**
   * Creates a new ElevatorManualTwo.
   */
  public ElevatorManualTwo(RightElevator m_rightElevator) {
    this.m_rightElevator = m_rightElevator;
    addRequirements(m_rightElevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.rightJoystick.getRawAxis(1) > 0.5){
      m_rightElevator.Elevator2Up();
    } else if (RobotContainer.rightJoystick.getRawAxis(1) < -0.5) {
      m_rightElevator.Elevator2Down();
    } else {
      m_rightElevator.StopElevator2();
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
