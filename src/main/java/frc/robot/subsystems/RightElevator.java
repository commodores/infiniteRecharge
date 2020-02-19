package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.commands.ElevatorManualTwo;

public class RightElevator extends SubsystemBase {

  private final TalonSRX rightElevator;
  
  public RightElevator() {

    rightElevator = new TalonSRX(ElevatorConstants.kelevatorMotorRightPort);

    rightElevator.configFactoryDefault();
    rightElevator.setNeutralMode(NeutralMode.Brake);
    rightElevator.set(ControlMode.PercentOutput, 0.0);

    setDefaultCommand(new ElevatorManualTwo(this));
  }

  public void Elevator2Up(){
    rightElevator.set(ControlMode.PercentOutput, -1);
  }

  public void Elevator2Down(){
    rightElevator.set(ControlMode.PercentOutput, 1);
  } 

  public void StopElevator2(){
    rightElevator.set(ControlMode.PercentOutput, 0.0);
  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
