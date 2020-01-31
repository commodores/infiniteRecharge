/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChuteConstants;

public class Chute extends SubsystemBase {
  /**
   * Creates a new Chute.
   */
  private final WPI_TalonSRX chuteMotor = new WPI_TalonSRX(ChuteConstants.kchuteMotor1Port);
  private final WPI_TalonSRX ChuteSlave = new WPI_TalonSRX(ChuteConstants.kchuteMotor2Port);
  public Chute() {
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
