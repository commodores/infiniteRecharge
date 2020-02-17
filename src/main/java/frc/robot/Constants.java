package frc.robot;


public final class Constants {
    
  public static final class DriveConstants {
    public static final int kLeftMasterPort = 3;
    public static final int kLeftSlave0Port = 4;
    
    public static final int kRightMasterPort = 1;
    public static final int kRightSlave0Port = 2;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kLeftJoystickPort = 1;
    public static final int kRightJoystickPort = 2;
    public static final int kArcadeJoysitckPort = 3;
    public static final int kXboxDriverControllerPort = 4;
  }

  public static final class pnuematicConstants{
    public static final int kSolenoid = 1;
    public static final int kSolenoid0 = 2;
  }

  public static final class TurretConstants {
    public static final int kturretMotorPort = 5;
    public static final int klimeLightHeight = 30;//inches
    public static final int ktargetHeight = 81;//inches
  }

  public static final class IntakeConstants{
    public static final int kintakeMotorPort = 6;
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
    public static final int kchuteMotor1Port = 11;
    public static final int kchuteMotor2Port = 12;
  }
  public static final class ElevatorConstants{
    public static final int kelevatorMotor1Port = 13;
    public static final int kelevatorMotor2Port = 14;
  }
}
