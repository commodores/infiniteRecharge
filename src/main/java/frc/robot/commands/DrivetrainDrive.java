package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DrivetrainDrive extends CommandBase {
private final DriveTrain m_drive;
private DoubleSupplier speed;
private DoubleSupplier turn;

    public DrivetrainDrive(DoubleSupplier speed, DoubleSupplier turn, DriveTrain m_subsystem) {
        m_drive = m_subsystem;
        addRequirements(m_subsystem);

        this.speed = speed;
        this.turn = turn;
    }

	@Override
    public void execute() {
        m_drive.arcadeDrive(speed.getAsDouble(), turn.getAsDouble());
    }
}