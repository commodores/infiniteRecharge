/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANPIDController;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

public class Shooter extends SubsystemBase {
  
  private final CANSparkMax shooter1;
  private final CANSparkMax shooter2;
  private final CANEncoder encoder;
  private final CANPIDController controller;

  double setpoint = 0;

  public Shooter() {
    shooter1 = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    shooter2 = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    
    shooter2.follow(shooter1, true);
    shooter1.set(0);

    shooter1.setIdleMode(IdleMode.kCoast);
    shooter2.setIdleMode(IdleMode.kCoast);

    encoder = shooter1.getEncoder();
    controller = shooter1.getPIDController();
    controller.setFeedbackDevice(encoder);
    
    stop();
    updateConstants();
  }

  public void set(double setpoint) {
    controller.setReference(setpoint, ControlType.kVelocity);
  }

  public void stop() {
    controller.setReference(0, ControlType.kDutyCycle);
  }

  public void updateConstants() {
    controller.setOutputRange(-1, 0);
    controller.setP(ShooterConstants.shooterP);
    controller.setI(ShooterConstants.shooterI);
    controller.setD(ShooterConstants.shooterD);
    controller.setFF(ShooterConstants.shooterF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
