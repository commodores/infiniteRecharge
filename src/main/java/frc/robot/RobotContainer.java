package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SimpleAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.LowerChute;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.UpperChute;


public class RobotContainer {
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Turret m_turret = new Turret();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final UpperChute m_upperChute = new UpperChute();
  private final LowerChute m_lowerChute = new LowerChute();
  private final LeftElevator m_leftElevator = new LeftElevator();
  private final RightElevator m_rightElevator = new RightElevator();
  
  private final SimpleAuto m_autoCommand = new SimpleAuto();

  public static final XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static final Joystick leftJoystick = new Joystick(OIConstants.kLeftJoystickPort);
  public static final Joystick rightJoystick = new Joystick(OIConstants.kRightJoystickPort);
  
  public RobotContainer() {

    configureButtonBindings();
              
  }

  private void configureButtonBindings() {

    new JoystickButton(rightJoystick, 6)
      .whenPressed(() -> m_lowerChute.ChuteDown())
      .whenReleased(() -> m_lowerChute.StopChute());

    new JoystickButton(rightJoystick, 5)
      .whenPressed(() -> m_lowerChute.ChuteUp())
      .whenReleased(() -> m_lowerChute.StopChute());

    new JoystickButton(m_driverController, Button.kBumperRight.value)
      .whenPressed(() -> m_shooter.set(.97))
      .whenReleased(() -> m_shooter.stop());

    new JoystickButton(m_driverController, Button.kA.value)
      .whenPressed(() -> m_upperChute.ChuteUp())
      .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(m_driverController, Button.kB.value)
      .whenPressed(() -> m_upperChute.ChuteDown())
      .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(rightJoystick, 8)
      .whenPressed(() -> m_intake.BallOut())
      .whenReleased(() -> m_intake.stopIntake()); 

    new JoystickButton(rightJoystick, 7)
        .whenPressed(() -> m_intake.BallIn())
        .whenReleased(() -> m_intake.stopIntake()); 

    new JoystickButton(rightJoystick, 3)
        .whenPressed(() -> m_intake.raiseIntake())
        .whenReleased(() -> m_intake.stopRetract());

    new JoystickButton(rightJoystick, 2)
      .whenPressed(() -> m_intake.lowerIntake())
      .whenReleased(() -> m_intake.stopRetract());

  }
    
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

}
