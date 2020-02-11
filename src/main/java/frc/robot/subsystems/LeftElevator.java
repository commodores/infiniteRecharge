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
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LeftElevator extends SubsystemBase {
  /**
   * Creates a new LeftElevator.
   * 
   */

   private final WPI_TalonSRX leftElevator;
  public LeftElevator() {

    leftElevator = new WPI_TalonSRX(ElevatorConstants.kelevatorMotor1Port);

    leftElevator.configFactoryDefault();

    leftElevator.setSafetyEnabled(false);

    leftElevator.setNeutralMode(NeutralMode.Brake);

    leftElevator.set(ControlMode.PercentOutput, 0.0);

  }

  public void ElevatorUp(double speed){
    leftElevator.set(ControlMode.PercentOutput, speed);
  }

  public void ElevatorDown(double speed){
    leftElevator.set(ControlMode.PercentOutput, speed);
  }

  public void StopElevator(double speed){
    leftElevator.set(0.0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
