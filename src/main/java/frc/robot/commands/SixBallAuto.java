/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SixBallAuto extends SequentialCommandGroup {
  /**
   * Creates a new SixBallAuto.
   */
  public SixBallAuto() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      //new AimTurret(),
      new ShootAuto(4000).withTimeout(1.25),
      new ParallelCommandGroup(
        new ShootAuto(4000),
        new UpperChuteAuto(),
        new LowerChuteAuto()).withTimeout(1.5),
      new StopShooterAuto().withTimeout(.001),
      new StopLowerChute().withTimeout(.001),
      new StopUpperChute().withTimeout(.001),
      new IntakeDownAuto().withTimeout(.001),
      new AutoDrive(1.3, .75),
      new ParallelCommandGroup(
        new AutoDrive(3.2, .4),
        new IntakeInAuto(),
        new LowerChuteAuto()).withTimeout(6),
      new StopIntakeAuto().withTimeout(.1),
      //new AimTurret(),
      new ParallelCommandGroup(
        new UpperChuteReverseAuto(),
        new LowerChuteReverseAuto()).withTimeout(.25),
      new StopLowerChute().withTimeout(.001),
      new StopUpperChute().withTimeout(.001),
      new ShootAuto(5100).withTimeout(1.25),
      new ParallelCommandGroup(
        new ShootAuto(5000),
        new UpperChuteAuto(),
        new LowerChuteAuto()).withTimeout(1.5),
      new StopShooterAuto().withTimeout(.001),
      new StopLowerChute().withTimeout(.001),
      new StopUpperChute().withTimeout(.001)
    );
  }
}
