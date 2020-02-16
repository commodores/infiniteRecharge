package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorShifter extends SubsystemBase {
  private final Solenoid m_motorSolenoid = new Solenoid(0);

  public MotorShifter() {
    
  }

  public void highGear() {
    m_motorSolenoid.set(true);
  }

  public void lowGear() {
    m_motorSolenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}