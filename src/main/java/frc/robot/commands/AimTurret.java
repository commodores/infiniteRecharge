package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AimTurret extends CommandBase {
  
  public AimTurret() {
    addRequirements(RobotContainer.m_turret);
    addRequirements(RobotContainer.m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //RobotContainer.m_limelight.setPipeline(0);
    //RobotContainer.m_limelight.setLedMode(2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.m_limelight.seesTarget()){
      if(RobotContainer.m_limelight.getX()>=5){
        RobotContainer.m_turret.turnTurret(-.3);
      }
      else if(RobotContainer.m_limelight.getX()>=3){
        RobotContainer.m_turret.turnTurret(-.2);
      }
      else if(RobotContainer.m_limelight.getX()>=1){
        RobotContainer.m_turret.turnTurret(-.1);
      }
      else if(RobotContainer.m_limelight.getX()<=-5){
        RobotContainer.m_turret.turnTurret(.3);
      }
      else if(RobotContainer.m_limelight.getX()<=-3){
        RobotContainer.m_turret.turnTurret(.2);
      }
      else if(RobotContainer.m_limelight.getX()<=-1){
        RobotContainer.m_turret.turnTurret(.1);
      }
      else {
        RobotContainer.m_turret.turnTurret(0);
      }
    } else {
      RobotContainer.m_turret.turnTurret(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_turret.turnTurret(0);
    //RobotContainer.m_limelight.setLedMode(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if(RobotContainer.m_limelight.getX()<=2 && RobotContainer.m_limelight.getX()>=-2){
    //  return true;
    //}
    return false;



  }
}