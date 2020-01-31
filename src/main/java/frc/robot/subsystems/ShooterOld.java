package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.CANPIDController;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterOld extends SubsystemBase {
  
  private final CANSparkMax shooter1;
  private final CANSparkMax shooter2;
  private final CANEncoder encoder;
  private final CANPIDController controller;
  

  double setpoint = 0;

  public ShooterOld() {
    

    shooter1 = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    shooter2 = new CANSparkMax(ShooterConstants.kshooterMotor1Port, MotorType.kBrushless);
    
    shooter1.restoreFactoryDefaults();
    shooter2.restoreFactoryDefaults();

    shooter2.follow(shooter1, true);
    shooter1.set(0);

    shooter1.setIdleMode(IdleMode.kCoast);
    shooter2.setIdleMode(IdleMode.kCoast);

    encoder = shooter1.getEncoder();
    controller = shooter1.getPIDController();
    controller.setFeedbackDevice(encoder);
    
    //stop();
    updateConstants();
  }

  public void set(double setpoint) {
    controller.setReference(setpoint, ControlType.kVelocity);
  }

  public void stop() {
    controller.setReference(0, ControlType.kDutyCycle);
  }

  public void updateConstants() {
    controller.setP(ShooterConstants.shooterP);
    controller.setI(ShooterConstants.shooterI);
    controller.setD(ShooterConstants.shooterD);
    controller.setFF(ShooterConstants.shooterF);
    controller.setOutputRange(-1, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
