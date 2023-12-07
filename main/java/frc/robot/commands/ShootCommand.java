package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Controlboard;
import frc.robot.subsystems.Shooter;
import frc.robot.utils.Constants;

public class ShootCommand extends CommandBase {
  private Shooter _shooter;
  private Controlboard _controlboard;

  public enum ShooterState { SHOOT, PREP, OFF }

  public ShootCommand(Shooter shooter, Controlboard controlboard) {
    _shooter = shooter;
    _controlboard = controlboard;
    addRequirements(_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    switch (getState()) {
      case SHOOT:
        _shooter.setFlywheel(_controlboard.getPower());
        _shooter.setFeeder(Constants.Shooter.FEEDER_POWER);
        break;
      case PREP:
        _shooter.setFlywheel(_controlboard.getPower());
        _shooter.setFeeder(0.0);
        break;
      case OFF:
      default:
        _shooter.setFlywheel(0.0);
        _shooter.setFeeder(0.0);
        break;
    }
  }

  private ShooterState getState() {
    if (_controlboard.isShootDesired()) {
      return ShooterState.SHOOT;
    }
    if (_controlboard.isPrepDesired()) {
      return ShooterState.PREP;
    }
    return ShooterState.OFF;
  }

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
