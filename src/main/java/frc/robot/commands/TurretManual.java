/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Turret;

public class TurretManual extends CommandBase {
  private final Turret m_turret;
  /**
   * Creates a new TurretManual.
   */
  public TurretManual(Turret m_turret) {
    this.m_turret = m_turret;
    addRequirements(m_turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = -RobotContainer.m_driverController.getRawAxis(4); 
    /*double rotation = -RobotContainer.m_driverController.getRawAxis(4);
    if( rotation > -0.1 && rotation < 0.1){
      rotation = 0;
    }
   */ 
    if( speed > -0.1 && speed < 0.1){
      speed = 0;
    }

    m_turret.turnTurret(speed*.5);

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
