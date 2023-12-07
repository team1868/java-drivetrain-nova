package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.utils.Constants;

public class UnlodgeCommand extends CommandBase {
  private Shooter _shooter;

  public UnlodgeCommand(Shooter shooter) {
    _shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    _shooter.setFeeder(Constants.Shooter.UNLODGE_POWER);
  }

  @Override
  public void execute() {}

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    _shooter.setFeeder(0.0);
    _shooter.setFlywheel(0.0);
  }
}
