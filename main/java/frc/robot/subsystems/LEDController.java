package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Ports;

public class LEDController extends SubsystemBase {
  private int _firstPixelHue = 0;
  private AddressableLED _ledStrip;
  private AddressableLEDBuffer _ledBuffer;

  public LEDController() {
    _ledStrip = new AddressableLED(Ports.LED_PORT);
    _ledBuffer = new AddressableLEDBuffer(Constants.LED.LENGTH);
    _ledStrip.setLength(Constants.LED.LENGTH);
    _ledStrip.start();
    _ledStrip.setData(_ledBuffer);
  }

  @Override
  public void periodic() {
    // Rainbow Sliding/Animated Effect!
    _firstPixelHue = (_firstPixelHue + 10) % 180;

    for (int i = 0; i < Constants.LED.LENGTH; i++) {
      int hue = (_firstPixelHue + (i * 180 / Constants.LED.LENGTH)) % 180;
      _ledBuffer.setHSV(i, hue, Constants.LED.SAT, Constants.LED.VALUE);
    }

    _ledStrip.setData(_ledBuffer);
  }
}
