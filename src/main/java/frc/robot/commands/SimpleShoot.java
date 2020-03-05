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
public class SimpleShoot extends SequentialCommandGroup {
  /**
   * Creates a new SImpleShoot.
   */
  public SimpleShoot() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      //new AimTurret(),
      new ShootAuto(4000).withTimeout(5),
      new ParallelCommandGroup(
        new ShootAuto(4000),
        new UpperChuteAuto(),
        new LowerChuteAuto()).withTimeout(2),
      new StopShooterAuto().withTimeout(.1),
      new StopLowerChute().withTimeout(.1),
      new StopUpperChute().withTimeout(.1),
      new AutoDrive(1, .5).withTimeout(3)
    );
  }
}
