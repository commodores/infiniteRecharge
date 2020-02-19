package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChuteConstants;

public class UpperChute extends SubsystemBase {

  private final TalonSRX upperChuteMotor;

  public UpperChute() {

    upperChuteMotor = new TalonSRX(ChuteConstants.kchuteMotorUpperPort);
    
    upperChuteMotor.configFactoryDefault();
    upperChuteMotor.setNeutralMode(NeutralMode.Coast);
    upperChuteMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void ChuteUp(){
    upperChuteMotor.set(ControlMode.PercentOutput, .35);
  }

  public void ChuteDown(){
    upperChuteMotor.set(ControlMode.PercentOutput, -.35);
  }

  public void StopChute(){
    upperChuteMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
