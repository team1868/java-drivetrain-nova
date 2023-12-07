package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.utils.Constants;
import frc.robot.utils.Ports;
import java.lang.Math;

public class Controlboard {
  public CommandXboxController _xboxDrive = new CommandXboxController(Ports.XBOX_USB_PORT);
  private int _prevPOV = -1;
  private boolean _prevPrepTrigger = false;
  private boolean _prepDesired = false;
  private GenericEntry _maxPowerEntry;

  public Controlboard() {
    // Config Shuffleboard
    ShuffleboardTab powerTab = Shuffleboard.getTab("Power Tab");
    _maxPowerEntry = powerTab.add("Max Power Output", Constants.Shooter.FEEDER_POWER).getEntry();
  }

  public double getThrottle() {
    return _xboxDrive.getLeftY();
  }

  public double getTurn() {
    return _xboxDrive.getRightX();
  }

  public boolean canTurnInPlace() {
    return _xboxDrive.rightBumper().getAsBoolean();
  }

  public boolean isShootDesired() {
    return _xboxDrive.leftTrigger().getAsBoolean();
  }

  public boolean isPrepDesired() {
    boolean curPrepTrigger = _xboxDrive.rightTrigger().getAsBoolean();
    if ((curPrepTrigger != _prevPrepTrigger) && (_xboxDrive.rightTrigger().getAsBoolean())) {
      _prepDesired = !_prepDesired;
    }
    _prevPrepTrigger = curPrepTrigger;

    return _prepDesired;
  }

  public void updatePower() {
    int curPOV = _xboxDrive.getHID().getPOV();
    double increment = 0.0;
    if (_prevPOV != curPOV) {
      switch (curPOV) {
        case 0:
          increment = 0.1;
          break;
        case 180:
          increment = -0.1;
          break;
        default:
          break;
      }
      if (increment != 0.0) {
        double flywheelPower = getPower() + increment;
        flywheelPower = Math.min(1.0, Math.max(0.0, flywheelPower));
        _maxPowerEntry.setDouble(flywheelPower);
      }
    }
    _prevPOV = curPOV;
  }

  public double getPower() {
    return _maxPowerEntry.getDouble(Constants.Shooter.FEEDER_POWER);
  }
}
