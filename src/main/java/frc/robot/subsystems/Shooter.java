package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  CANSparkMax m_leftShooterMotor, m_rightShooterMotor;

  public Shooter() {
    m_leftShooterMotor = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    m_rightShooterMotor = new CANSparkMax(ShooterConstants.kshooterMotor2Port, MotorType.kBrushless);
    
    m_rightShooterMotor.restoreFactoryDefaults();
    m_leftShooterMotor.restoreFactoryDefaults();
    
    m_rightShooterMotor.setInverted(true);
    m_leftShooterMotor.setOpenLoopRampRate(ShooterConstants.SHOOTER_VOLTAGE_RAMP_RATE);
    m_rightShooterMotor.setOpenLoopRampRate(ShooterConstants.SHOOTER_VOLTAGE_RAMP_RATE);
  }

  public void set(double speed) {
    m_leftShooterMotor.set(speed);
    m_rightShooterMotor.set(speed);
  }

  public void stop() {
    m_leftShooterMotor.set(0);
    m_rightShooterMotor.set(0);
  }

  public double getAverageSpeed() {
    return (getLeftSpeed() + getRightSpeed()) / 2.0;
  }

  public double getRightSpeed() {
    return m_rightShooterMotor.getEncoder().getVelocity();
  }

  public double getLeftSpeed() {
    return m_leftShooterMotor.getEncoder().getVelocity();
  }

}