package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AimTurret;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.SimpleShoot;
import frc.robot.commands.SixBallAuto;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.LowerChute;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.UpperChute;
import frc.robot.subsystems.VelocityShooter;


public class RobotContainer {
  public static final DriveTrain m_drivetrain = new DriveTrain();
  public static final Arm m_arm = new Arm();
  public static final Turret m_turret = new Turret();
  public static Intake m_intake = new Intake();
  //private final Shooter m_shooter = new Shooter();
  public static VelocityShooter m_shooter = new VelocityShooter();
  public static UpperChute m_upperChute = new UpperChute();
  public static LowerChute m_lowerChute = new LowerChute();
  public static LeftElevator m_leftElevator = new LeftElevator();
  public static RightElevator m_rightElevator = new RightElevator();
  public static LimeLight m_limelight = new LimeLight();
  
  public static final XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static final Joystick leftJoystick = new Joystick(OIConstants.kLeftJoystickPort);
  public static final Joystick rightJoystick = new Joystick(OIConstants.kRightJoystickPort);
  
  /**
   * Instantiation of autonomous chooser.
   * Allows operators to preselect which autonomous command to run during autonomous period.
   */
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();

  public RobotContainer() {

    configureButtonBindings();

     /* Initialize various systems on robotInit. */
     this.initializeStartup();

     /* Initialize autonomous command chooser and display on the SmartDashboard. */
     this.initializeAutoChooser();
              
  }

  private void configureButtonBindings() {

    new JoystickButton(rightJoystick, 6)
      .whenPressed(() -> m_lowerChute.ChuteDown())
      .whenReleased(() -> m_lowerChute.StopChute());

    new JoystickButton(rightJoystick, 5)
      .whenPressed(() -> m_lowerChute.ChuteUp())
      .whenReleased(() -> m_lowerChute.StopChute());

//Test these
    new JoystickButton(m_driverController, Button.kBumperRight.value)
      .whenPressed(() -> m_shooter.shoot(5350))
      .whenReleased(() -> m_shooter.stopShooter());

    new JoystickButton(m_driverController, Button.kX.value)
      .whenPressed(() -> m_shooter.shoot(3750))
      .whenReleased(() -> m_shooter.stopShooter());
      
    new JoystickButton(m_driverController, Button.kY.value)
      .whenPressed(() -> m_shooter.shoot(4600))
      .whenReleased(() -> m_shooter.stopShooter());

    //new JoystickButton(rightJoystick, 1)
    //  .whenPressed(new AimTurret(Turret));
/*
    new JoystickButton(m_driverController, Button.kBumperRight.value)
      .whenPressed(() -> m_shooter.set(.90))
      .whenReleased(() -> m_shooter.stop());

    new JoystickButton(rightJoystick, 1)
      .whenPressed(() -> m_shooter.set(.65))
      .whenReleased(() -> m_shooter.stop());
*/
    new JoystickButton(rightJoystick, 2)
    .whenPressed(() -> m_upperChute.ChuteUp())
    .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(rightJoystick, 3)
      .whenPressed(() -> m_upperChute.ChuteDown())
      .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(m_driverController, Button.kA.value)
      .whenPressed(() -> m_upperChute.ChuteUp())
      .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(m_driverController, Button.kB.value)
      .whenPressed(() -> m_upperChute.ChuteDown())
      .whenReleased(() -> m_upperChute.StopChute());

    new JoystickButton(rightJoystick, 9)
      .whenPressed(() -> m_intake.BallOut())
      .whenReleased(() -> m_intake.stopIntake()); 

    new JoystickButton(rightJoystick, 8)
        .whenPressed(() -> m_intake.BallIn())
        .whenReleased(() -> m_intake.stopIntake());
    
    new JoystickButton(m_driverController, Button.kBack.value)
      //.whenPressed(() -> m_arm.armUp());
      .whenPressed(() -> m_arm.armManual(-.3))
      .whenReleased(() -> m_arm.armStop());

    new JoystickButton(m_driverController, Button.kStart.value)
    //.whenPressed(() -> m_arm.armDown());
    .whenPressed(() -> m_arm.armManual(.3))
    .whenReleased(() -> m_arm.armStop());
    
  }
  /**
   * Various methods to run when robot is initialized.
   * Cannot put these in robotInit() in Robot.java because subsystems may not be instantiated at that point.
   */
  private void initializeStartup()
  {
    /* Turn off Limelight LED when first started up so it doesn't blind drive team. */
    m_limelight.setLedMode(0);

    m_turret.setDefaultCommand(
      new AimTurret());
  }
  /**
   * Set options for autonomous command chooser and display them for selection on the SmartDashboard.
   * Using string chooser rather than command chooser because if using a command chooser, will instantiate
   * all the autonomous commands. This may cause problems (e.g. initial trajectory position is from a
   * different command's path).
   */
  private void initializeAutoChooser()
  {
    /* Add options (which autonomous commands can be selected) to chooser. */
    m_autoChooser.setDefaultOption("Just Choot 'em'", "simpleShoot");
    m_autoChooser.addOption("Drive forward off line", "forward1");
    m_autoChooser.addOption("Drive reverse off line", "reverse1");
    m_autoChooser.addOption("6 Balls is nuts!!!", "sixBall");
    //m_autoChooser.addOption("Three Ball", "threeBall");
    //m_autoChooser.addOption("Six Ball", "sixBall");

    /* Display chooser on SmartDashboard for operators to select which autonomous command to run during the auto period. */
    SmartDashboard.putData("Autonomous Command", m_autoChooser);
  }  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    switch (m_autoChooser.getSelected())
    {
      case "simpleShoot":
        return new SimpleShoot();
      case "forward1":
        return new AutoDrive(-1,.5)
        .withTimeout(5);
      case "reverse1":
        return new AutoDrive(1,.5)
        .withTimeout(5);
      case "sixBall":
        return new SixBallAuto();
      default:
        System.out.println("\nError selecting autonomous command:\nCommand selected: " + m_autoChooser.getSelected() + "\n");
        return null;
    }    
  }

}
