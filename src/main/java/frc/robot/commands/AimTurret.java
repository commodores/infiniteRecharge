package frc.robot.commands;

import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AimTurret extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Turret m_turret;
  private final LimeLight m_limelight;
  

  public AimTurret(Turret turret, LimeLight limelight) {
    this.m_turret = turret;
    this.m_limelight = limelight;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_limelight.setPipeline(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_limelight.setPipeline(0);
   // limelight.setLedMode(0);
      if(m_limelight.getX()>=3){
        m_turret.turnTurret(-.25);
      }
      if(m_limelight.getX()<=-3){
        m_turret.turnTurret(.25);
      }

    //move it the direction of the target

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turret.turnTurret(0);
    m_limelight.setPipeline(1);
    m_limelight.setLedMode(0);
    //set turret to 0 - stop moving

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_limelight.getX()<=1 && m_limelight.getX()>=-1){
      return true;
    }
    return false;



  }
}