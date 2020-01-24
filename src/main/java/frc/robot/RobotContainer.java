/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import static edu.wpi.first.wpilibj.XboxController.Button;

import javax.annotation.meta.When;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SimpleAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MotorShifter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final MotorShifter m_shifter = new MotorShifter();
  private final Turret m_turret = new Turret();
  private final Intake m_intake = new Intake();

  private final SimpleAuto m_autoCommand = new SimpleAuto();

  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
  /*
    m_drivetrain.setDefaultCommand(
        new RunCommand(() -> m_drivetrain.arcadeDrive(
            m_driverController.getX(Hand.kLeft),
            m_driverController.getY(Hand.kRight)),m_drivetrain));
    */        
    m_drivetrain.setDefaultCommand(new RunCommand(() -> m_drivetrain.curvatureDrive(
      m_driverController.getRawAxis(3) - m_driverController.getRawAxis(2), 
      m_driverController.getRawAxis(0), 
      m_driverController.getRawButton(2)), m_drivetrain));
      
      
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kA.value)
      .whenPressed(new InstantCommand(m_shifter::lowGear, m_shifter));

    new JoystickButton(m_driverController, Button.kB.value)
      .whenPressed(new InstantCommand(m_shifter::highGear, m_shifter));

    new JoystickButton(m_driverController, Button.kX.value)
      .whenPressed(() -> m_turret.turnTurret(.25))
      .whenReleased(() -> m_turret.stopTurret());

    new JoystickButton(m_driverController, Button.kY.value)
      .whenPressed(() -> m_turret.turnTurret(-.25))
      .whenReleased(() -> m_turret.stopTurret());

    new JoystickButton(m_driverController,Button.kBumperLeft.value)
      .whenPressed(() -> m_intake.BallIn(.25))
      .whenReleased(() -> m_intake.stopIntake(0));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
