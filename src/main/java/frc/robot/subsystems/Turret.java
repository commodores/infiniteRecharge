/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {
  /**
   * Creates a new Turret.
   */
  private final TalonSRX turretMotor;

  public Turret() {
    turretMotor = new TalonSRX(TurretConstants.kturretMotorPort);
    
    turretMotor.configFactoryDefault();
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    turretMotor.setNeutralMode(NeutralMode.Brake);
    turretMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void turnTurret(double speed){
    turretMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stopTurret(){
    turretMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}