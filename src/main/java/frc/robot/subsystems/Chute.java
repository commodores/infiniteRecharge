/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChuteConstants;

public class Chute extends SubsystemBase {
  /**
   * Creates a new Chute.
   */
  private final WPI_TalonSRX ChuteMotor;
  private final WPI_TalonSRX ChuteSlave;

  public Chute() {

  ChuteSlave = new WPI_TalonSRX(ChuteConstants.kchuteMotor2Port);
  ChuteMotor = new WPI_TalonSRX(ChuteConstants.kchuteMotor1Port);

    
  ChuteMotor.configFactoryDefault();
  ChuteSlave.configFactoryDefault();

  ChuteSlave.follow(ChuteMotor);
  
  ChuteMotor.setSafetyEnabled(false);
  ChuteSlave.setSafetyEnabled(false);

  ChuteMotor.setNeutralMode(NeutralMode.Coast);
  ChuteSlave.setNeutralMode(NeutralMode.Coast);

  ChuteMotor.set(ControlMode.PercentOutput, 0.0);

  

  }

  public void ChuteUp(double speed){
    ChuteMotor.set(ControlMode.PercentOutput, speed);
  }

  public void ChuteDown(double speed){
    ChuteMotor.set(ControlMode.PercentOutput, speed);
  }

  public void StopChute(double speed){
    ChuteMotor.set(0.0);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
