package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

public final class Constants {
    
  public static final class DriveConstants {
    public static final int kLeftMasterPort = 3;
    public static final int kLeftSlave0Port = 4;
    
    public static final int kRightMasterPort = 1;
    public static final int kRightSlave0Port = 2;

    public static final int kPigeonPort = 9;

    public static final double kAutoSpeed = .4;
    public static final double kDriveTrainGain = .015;

    public static final double drivetrainEncoderConversionFactor = Units.inchesToMeters(6 * Math.PI) / 13;

    public static final double joystickSpeedConstant = 1.2;
    public static final double joystickTurnConstant = 1.4;
    public static final double minimumJoystickInput = 0.1;

    public static final double ksVolts = 0.188;
    public static final double kvVoltSecondsPerMeter = 3.51;
    public static final double kaVoltSecondsSquaredPerMeter = 0.497;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 16;

    public static final double kTrackwidthMeters = 0.63;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kLeftJoystickPort = 1;
    public static final int kRightJoystickPort = 2;
    public static final int kArcadeJoysitckPort = 3;
    public static final int kXboxDriverControllerPort = 4;
  }

  public static final class TurretConstants {
    public static final int kturretMotorPort = 5;
    public static final int klimeLightHeight = 30;//inches
    public static final int ktargetHeight = 81;//inches
  }

  public static final class IntakeConstants{
    public static final int kintakeMotorPort = 10;
    
  }

  public static final class ArmConstants{
    public static final int kArmPort = 6;
  }

  public static final class ShooterConstants{
    public static final int kshooterMotor1Port = 7;
    public static final int kshooterMotor2Port = 8;
    public static final double shooterP = 0.0011;
    public static final double shooterI = 0;
    public static final double shooterD = 4;
    public static final double shooterF = 0.00017;
    public static final double MAX_RPM = 5700;
    public static final double SHOOTER_VOLTAGE_RAMP_RATE = .2;

  }
  public static final class ChuteConstants{
    public static final int kchuteMotorUpperPort = 11;
    public static final int kchuteMotorLowerPort = 12;
  }
  public static final class ElevatorConstants{
    public static final int kelevatorMotorRightPort = 13;
    public static final int kelevatorMotorLeftPort = 14;
  }
}
