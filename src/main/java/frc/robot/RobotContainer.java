package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import static edu.wpi.first.wpilibj.XboxController.Button;

import java.util.List;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.DriveConstants;
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
  public static final DriveTrain m_drivetrain = new DriveTrain();
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
      .whenPressed(() -> m_shooter.set(.98))
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
/*
    new JoystickButton(rightJoystick, 3)
        .whenPressed(() -> m_intake.raiseIntake())
        .whenReleased(() -> m_intake.stopRetract());

    new JoystickButton(rightJoystick, 2)
      .whenPressed(() -> m_intake.lowerIntake())
      .whenReleased(() -> m_intake.stopRetract());
*/
  }
    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(DriveConstants.kMaxSpeedMetersPerSecond,
                             DriveConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_drivetrain::getPose,
        new RamseteController(DriveConstants.kRamseteB, DriveConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                   DriveConstants.kvVoltSecondsPerMeter,
                                   DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        m_drivetrain::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_drivetrain::tankDriveVolts,
        m_drivetrain
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0));
  }

}
