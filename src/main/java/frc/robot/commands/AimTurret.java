package frc.robot.commands;

import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AimTurret extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Turret turret;
  private final LimeLight limelight;
  

  public AimTurret(Turret turret, LimeLight limelight) {
    this.turret = turret;
    this.limelight = limelight;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setPipeline(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    limelight.setPipeline(0);
   // limelight.setLedMode(0);
      if(limelight.getX()>=3){
        turret.turnTurret(-.25);
      }
      if(limelight.getX()<=-3){
        turret.turnTurret(.25);
      }

    //move it the direction of the target

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.turnTurret(0);
    limelight.setPipeline(1);
    //set turret to 0 - stop moving

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(limelight.getX()<=1 && limelight.getX()>=-1){
      return true;
    }
    return false;



  }
}