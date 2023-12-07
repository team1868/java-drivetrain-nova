package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Ports;

public class Shooter extends SubsystemBase {
  private VictorSP _flywheelMotor = new VictorSP(Ports.FLYWHEEL_MOTOR_PORT);
  private VictorSP _feederMotor = new VictorSP(Ports.FEEDER_MOTOR_PORT);

  public Shooter() {
    _feederMotor.setInverted(true);
  }

  public void setFeeder(double power) {
    _flywheelMotor.set(power);
  }

  public void setFlywheel(double power) {
    _feederMotor.set(power);
  }
}
