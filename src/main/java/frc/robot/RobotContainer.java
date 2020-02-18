package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import static edu.wpi.first.wpilibj.XboxController.Button;


import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.DrivetrainDrive;
import frc.robot.commands.SimpleAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.LowerChute;
import frc.robot.subsystems.MotorShifter;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.UpperChute;
import edu.wpi.first.wpilibj.Compressor;


public class RobotContainer {
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final MotorShifter m_shifter = new MotorShifter();
  private final Compressor m_compressor = new Compressor();
  private final Turret m_turret = new Turret();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final UpperChute m_upperChute = new UpperChute();
  private final LowerChute m_lowerChute = new LowerChute();
  private final LeftElevator m_leftElevator = new LeftElevator();
  private final RightElevator m_rightElevator = new RightElevator();
  
  private final SimpleAuto m_autoCommand = new SimpleAuto();

  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  Joystick leftJoystick = new Joystick(OIConstants.kLeftJoystickPort);
  Joystick rightJoystick = new Joystick(OIConstants.kRightJoystickPort);
  
  public RobotContainer() {

    configureButtonBindings();
         
    m_drivetrain.setDefaultCommand(new DrivetrainDrive(
      () -> applyJoystickDeadBand((m_driverController.getTriggerAxis(Hand.kRight)-m_driverController.getTriggerAxis(Hand.kLeft))) * DriveConstants.joystickSpeedConstant,
      () -> applyJoystickDeadBand(m_driverController.getY(Hand.kLeft)) * DriveConstants.joystickTurnConstant,
      m_drivetrain));
      
  }

  private void configureButtonBindings() {

    new JoystickButton(m_driverController, Button.kX.value)
      .whenPressed(() -> m_turret.turnTurret(.25))
      .whenReleased(() -> m_turret.stopTurret());

    new JoystickButton(m_driverController, Button.kY.value)
      .whenPressed(() -> m_turret.turnTurret(-.25))
      .whenReleased(() -> m_turret.stopTurret());
       
    new JoystickButton(m_driverController,Button.kBumperLeft.value)
      .whenPressed(() -> m_intake.BallIn())
      .whenReleased(() -> m_intake.stopIntake());

    new JoystickButton(m_driverController, Button.kBumperRight.value)
      .whenPressed(() -> m_shooter.set(1))
      .whenReleased(() -> m_shooter.stop());

    new JoystickButton(m_driverController, Button.kA.value)
      .whenPressed(() -> m_lowerChute.ChuteUp())
      .whenReleased(() -> m_lowerChute.StopChute());

    new JoystickButton(m_driverController, Button.kB.value)
    .whenPressed(() -> m_upperChute.ChuteUp())
    .whenReleased(() -> m_upperChute.StopChute());

  }
    
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

  public double applyJoystickDeadBand(double originalValue) {
    if (Math.abs(originalValue) < DriveConstants.minimumJoystickInput) return 0;

    if (originalValue < 0) return originalValue + DriveConstants.minimumJoystickInput;
    return originalValue - DriveConstants.minimumJoystickInput;
  }
}
