/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

  private final TalonSRX armMotor;

  public Arm() {
        
    armMotor = new TalonSRX(ArmConstants.kArmPort);

    int absolutePosition = 0;
    armMotor.configNeutralDeadband(0.001, 10); 
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute); 
    absolutePosition = armMotor.getSensorCollection().getPulseWidthPosition(); 
    armMotor.getSensorCollection().setQuadraturePosition(absolutePosition, 0); 
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    armMotor.setSensorPhase(false);

    armMotor.config_kP(0, 0.85);
    armMotor.config_kI(0, 0.002);
    armMotor.config_kD(0, 0.0);
    armMotor.config_kF(0, .5);  

    
  }

  public int getArmPostion(){
    return armMotor.getSelectedSensorPosition();
  }

  public void setArmPosition(){
    armMotor.set(ControlMode.PercentOutput, .5);
  }

  
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
