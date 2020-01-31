package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  CANSparkMax m_leftShooterMotor, m_rightShooterMotor;

  double m_setpoint = 0;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    m_leftShooterMotor = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    m_rightShooterMotor = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    m_rightShooterMotor.setInverted(true);
    m_leftShooterMotor.setOpenLoopRampRate(ShooterConstants.SHOOTER_VOLTAGE_RAMP_RATE);
    m_rightShooterMotor.setOpenLoopRampRate(ShooterConstants.SHOOTER_VOLTAGE_RAMP_RATE);

    m_leftShooterMotor.burnFlash();
    m_rightShooterMotor.burnFlash();
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

  public double getSetpoint() {
    return m_setpoint;
  }

  public void setSetpoint(double setpoint) {
    m_setpoint = setpoint;
  }
}