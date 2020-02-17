package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class RightElevator extends SubsystemBase {

  private final TalonSRX rightElevator;
  private final Solenoid m_ratchetSolenoid2 = new Solenoid(2);
  
  public RightElevator() {

    rightElevator = new TalonSRX(ElevatorConstants.kelevatorMotorRightPort);

    rightElevator.configFactoryDefault();
    rightElevator.setNeutralMode(NeutralMode.Brake);
    rightElevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void Elevator2Up(){
    rightElevator.set(ControlMode.PercentOutput, -.5);
  }

  public void Elevator2Down(){
    rightElevator.set(ControlMode.PercentOutput, .5);
  } 

  public void StopElevator2(){
    rightElevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void ratchetOn(){
    m_ratchetSolenoid2.set(true);
  }

  public void ratchetOff(){
    m_ratchetSolenoid2.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
