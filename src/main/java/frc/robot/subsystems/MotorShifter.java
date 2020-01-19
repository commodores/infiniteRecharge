/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorShifter extends SubsystemBase {
  /**
   * Creates a new MotorShifter.
   */
  private Compressor compressor = new Compressor();
  private final Solenoid m_motorSolenoid = new Solenoid(0);

  public MotorShifter() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void highGear() {
    m_motorSolenoid.set(true);
  }

  public void lowGear() {
    m_motorSolenoid.set(false);
  }
}
