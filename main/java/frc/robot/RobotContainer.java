package frc.robot;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.UnlodgeCommand;
import frc.robot.subsystems.Controlboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LEDController;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  private Drivetrain _drivetrain = new Drivetrain();
  private Shooter _shooter = new Shooter();
  private Controlboard _controlboard = new Controlboard();
  private LEDController _ledController = new LEDController();
  private DriveCommand _driveCommand = new DriveCommand(_drivetrain, _controlboard);
  private ShootCommand _shootCommand = new ShootCommand(_shooter, _controlboard);
  private UnlodgeCommand _unlodgeCommand = new UnlodgeCommand(_shooter);

  public RobotContainer() {
    _drivetrain.setDefaultCommand(_driveCommand);
    _shooter.setDefaultCommand(_shootCommand);

    _controlboard._xboxDrive.y().whileTrue(_unlodgeCommand);
  }

  public void periodic() {
    _controlboard.updatePower();
  }
}
