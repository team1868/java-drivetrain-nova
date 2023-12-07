package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Ports;

public class Drivetrain extends SubsystemBase {
  private WPI_TalonSRX _leftPrimary = new WPI_TalonSRX(Ports.LEFT_DRIVE_PRIMARY_ID);
  private WPI_TalonSRX _rightPrimary = new WPI_TalonSRX(Ports.RIGHT_DRIVE_PRIMARY_ID);
  private WPI_TalonSRX _leftSecondary = new WPI_TalonSRX(Ports.LEFT_DRIVE_SECONDARY_ID);
  private WPI_TalonSRX _rightSecondary = new WPI_TalonSRX(Ports.RIGHT_DRIVE_SECONDARY_ID);
  private DifferentialDrive _differentialDrive = new DifferentialDrive(_leftPrimary, _rightPrimary);

  /* Control Utils */
  private static TalonSRXConfiguration _driveSRXConfig = new TalonSRXConfiguration();

  public Drivetrain() {
    initializeDriveConfig();

    _rightPrimary.configFactoryDefault();
    _rightSecondary.configFactoryDefault();
    _leftPrimary.configFactoryDefault();
    _leftSecondary.configFactoryDefault();

    _rightPrimary.configAllSettings(_driveSRXConfig);
    _rightSecondary.configAllSettings(_driveSRXConfig);
    _leftPrimary.configAllSettings(_driveSRXConfig);
    _leftSecondary.configAllSettings(_driveSRXConfig);

    _rightPrimary.setInverted(true);
    _rightSecondary.setInverted(true);

    _rightSecondary.follow(_rightPrimary);
    _leftSecondary.follow(_leftPrimary);
  }

  public void arcadeDrive(double thrust, double rotate, boolean squareInputs) {
    _differentialDrive.arcadeDrive(thrust, rotate, squareInputs);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean allowTurnInPlace) {
    _differentialDrive.curvatureDrive(
        (xSpeed / 2), zRotation, allowTurnInPlace
    ); // scaling by 2 for Craftapalooza demo 2023
  }

  private static void initializeDriveConfig() {
    _driveSRXConfig.slot0.kP = Constants.Drivetrain.DRIVE_PFAC;
    _driveSRXConfig.slot0.kI = Constants.Drivetrain.DRIVE_IFAC;
    _driveSRXConfig.slot0.kD = Constants.Drivetrain.DRIVE_DFAC;
    _driveSRXConfig.slot0.kF = Constants.Drivetrain.DRIVE_FFAC;

    _driveSRXConfig.peakCurrentLimit =
        (int) Constants.Drivetrain.DRIVE_CONTINUOUS_SUPPLY_CURRENT_LIMIT;
    _driveSRXConfig.continuousCurrentLimit =
        (int) Constants.Drivetrain.DRIVE_CONTINUOUS_STATOR_CURRENT_LIMIT;
    _driveSRXConfig.peakCurrentDuration = (int) Constants.Drivetrain.DRIVE_PEAK_CURRENT_DURATION;
    _driveSRXConfig.openloopRamp = Constants.Drivetrain.DRIVE_OPEN_LOOP_RAMP;
    _driveSRXConfig.closedloopRamp = Constants.Drivetrain.DRIVE_CLOSED_LOOP_RAMP;
  }
}
