package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChuteConstants;

public class LowerChute extends SubsystemBase {
  private final TalonSRX lowerChuteMotor;

  public LowerChute() {

    lowerChuteMotor = new TalonSRX(ChuteConstants.kchuteMotorLowerPort);
    
    lowerChuteMotor.configFactoryDefault();
    lowerChuteMotor.setNeutralMode(NeutralMode.Coast);
    lowerChuteMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void ChuteUp(){
    lowerChuteMotor.set(ControlMode.PercentOutput, -.35);
  }

  public void ChuteDown(){
    lowerChuteMotor.set(ControlMode.PercentOutput, .35);
  }

  public void StopChute(){
    lowerChuteMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
