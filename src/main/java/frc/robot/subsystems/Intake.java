package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  private final TalonSRX IntakeMotor;

  public Intake() {
    IntakeMotor = new TalonSRX(IntakeConstants.kintakeMotorPort);

    IntakeMotor.configFactoryDefault();
    IntakeMotor.setNeutralMode(NeutralMode.Coast);
    IntakeMotor.set(ControlMode.PercentOutput, 0.0);
  }
  public void BallIn(){
    IntakeMotor.set(ControlMode.PercentOutput, .5);
  }
  public void BallOut(){
    IntakeMotor.set(ControlMode.PercentOutput, -.5);
  }
  public void stopIntake(){
    IntakeMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}