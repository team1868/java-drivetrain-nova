package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Controlboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.Constants;

public class DriveCommand extends CommandBase {
  private Drivetrain _drivetrain;
  private Controlboard _controlboard;

  public DriveCommand(Drivetrain drivetrain, Controlboard controlboard) {
    _drivetrain = drivetrain;
    _controlboard = controlboard;
    addRequirements(_drivetrain);
  }

  @Override
  public void execute() {
    double throttle = _controlboard.getThrottle();
    double turn = _controlboard.getTurn();
    _drivetrain.curvatureDrive(
        Math.abs(throttle) < Constants.Controllers.XBOX_DRIVE_DEADBAND ? 0.0 : throttle,
        Math.abs(turn) < Constants.Controllers.XBOX_DRIVE_DEADBAND ? 0.0 : turn,
        _controlboard.canTurnInPlace()
    );
  }
}
