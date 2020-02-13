/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class RightElevator extends SubsystemBase {
  /**
   * Creates a new RightElevator.
   */
  private final WPI_TalonSRX rightElevator;
  public RightElevator() {

    rightElevator = new WPI_TalonSRX(ElevatorConstants.kelevatorMotor2Port);

    rightElevator.configFactoryDefault();
    
    rightElevator.setNeutralMode(NeutralMode.Brake);

    rightElevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void Elevator2Up(double speed){
    rightElevator.set(ControlMode.PercentOutput, speed);
  }

  public void Elevator2Down(double speed){
    rightElevator.set(ControlMode.PercentOutput, speed);
  } 

  public void StopElevator2(double speed){
    rightElevator.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
