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

public class LowerChute extends SubsystemBase {
  /**
   * Creates a new Chute.
   */
  private final WPI_TalonSRX lowerChuteMotor;

  public LowerChute() {

  lowerChuteMotor = new WPI_TalonSRX(ChuteConstants.kchuteMotor1Port);
  
    
  lowerChuteMotor.configFactoryDefault();
  
  lowerChuteMotor.setNeutralMode(NeutralMode.Coast);

  lowerChuteMotor.set(ControlMode.PercentOutput, 0.0);

  }

  public void ChuteUp(double speed){
    lowerChuteMotor.set(ControlMode.PercentOutput, speed);
  }

  public void ChuteDown(double speed){
    lowerChuteMotor.set(ControlMode.PercentOutput, speed);
  }

  public void StopChute(double speed){
    lowerChuteMotor.set(0.0);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
