package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.*;

public class LeftElevator extends SubsystemBase {
  private final TalonSRX leftElevator;
  
  public LeftElevator() {

    leftElevator = new TalonSRX(ElevatorConstants.kelevatorMotorLeftPort);

    leftElevator.configFactoryDefault();
    leftElevator.setNeutralMode(NeutralMode.Brake);
    leftElevator.set(ControlMode.PercentOutput, 0.0);

    setDefaultCommand(new ElevatorManual(this));
  }

  public void ElevatorUp(){
    leftElevator.set(ControlMode.PercentOutput, -1);
  }

  public void ElevatorDown(){
    leftElevator.set(ControlMode.PercentOutput, 1);
  }

  public void StopElevator(){
    leftElevator.set(ControlMode.PercentOutput, 0.0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
