package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LeftElevator extends SubsystemBase {
  private final TalonSRX leftElevator;
  private final Solenoid m_ratchetSolenoid1 = new Solenoid(1);
  
  public LeftElevator() {

    leftElevator = new TalonSRX(ElevatorConstants.kelevatorMotor1Port);

    leftElevator.configFactoryDefault();
    leftElevator.setNeutralMode(NeutralMode.Brake);
    leftElevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void ElevatorUp(){
    leftElevator.set(ControlMode.PercentOutput, -.5);
  }

  public void ElevatorDown(){
    leftElevator.set(ControlMode.PercentOutput, .5);
  }

  public void StopElevator(){
    leftElevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void ratchetOn(){
    m_ratchetSolenoid1.set(true);
  }

  public void ratchetOff(){
    m_ratchetSolenoid1.set(false);
  } 
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
