package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  private final TalonSRX intakeMotor;
  private final VictorSPX intakeRetractMotor;

  public Intake() {
    intakeMotor = new TalonSRX(IntakeConstants.kintakeMotorPort);
    intakeRetractMotor = new VictorSPX(IntakeConstants.kintakeRetractPort);

    intakeMotor.configFactoryDefault();
    intakeRetractMotor.configFactoryDefault();

    intakeMotor.setNeutralMode(NeutralMode.Coast);
    intakeRetractMotor.setNeutralMode(NeutralMode.Brake);

    intakeMotor.set(ControlMode.PercentOutput, 0.0);
    intakeRetractMotor.set(ControlMode.PercentOutput, 0.0);
  }
  
  public void BallIn(){
    intakeMotor.set(ControlMode.PercentOutput, .5);
  }
  
  public void BallOut(){
    intakeMotor.set(ControlMode.PercentOutput, -.5);
  }
  
  public void stopIntake(){
    intakeMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void raiseIntake(){
    intakeRetractMotor.set(ControlMode.PercentOutput, .5);
  }

  public void lowerIntake(){
    intakeRetractMotor.set(ControlMode.PercentOutput, -.5);
  }

  public void stopRetract(){
    intakeRetractMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}