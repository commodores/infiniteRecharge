/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

  private final TalonSRX armMotor;
  private int initPosition;

  public Arm() {
        
    armMotor = new TalonSRX(ArmConstants.kArmPort);

    armMotor.configFactoryDefault();

    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    armMotor.setSensorPhase(true);
    armMotor.setInverted(false);

    armMotor.setNeutralMode(NeutralMode.Brake);

    armMotor.configAllowableClosedloopError(0, 25, 300);
   
    armMotor.selectProfileSlot(0, 0);
    armMotor.config_kF(0, .5, 10);
    armMotor.config_kP(0, .5, 10);
    armMotor.config_kI(0, 0.0001, 10);
    armMotor.config_kD(0, 0, 10);

    armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
    armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);

    armMotor.configNominalOutputForward(0, 10);
    armMotor.configNominalOutputReverse(0, 10);
    armMotor.configPeakOutputForward(1, 10);
    armMotor.configPeakOutputReverse(-1, 10);

    armMotor.configMotionAcceleration(2052, 10);
    armMotor.configMotionCruiseVelocity(1056, 10);

    initPosition = armMotor.getSelectedSensorPosition();
    
  }

  public int getArmPostion(){
    return armMotor.getSelectedSensorPosition();
  }

  public void motionMagic(double pos){
    armMotor.set(ControlMode.MotionMagic, pos);
  }

  public void armUp(){
    armMotor.set(ControlMode.MotionMagic, initPosition - 500);
  }

  public void armDown(){
    armMotor.set(ControlMode.MotionMagic, initPosition - 3700);
  }

  public void armManual(double speed){
    armMotor.set(ControlMode.PercentOutput, speed);
  }

  public void armStop(){
    armMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public int getInitPosition(){
    return initPosition;
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
