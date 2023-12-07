package frc.robot.utils;

public class Constants {
  /* SUBSYSTEMS */

  public static class LED {
    public static final int LENGTH = 38;
    public static final int SAT = 200; // do not change
    public static final int VALUE = 150; // do not change
  }

  public static class Shooter {
    public static final double FEEDER_POWER = 0.5;
    public static final double UNLODGE_POWER = -0.5;
  }

  public static class Drivetrain {
    /* Drive Motor Characterization Values */
    public static final double DRIVE_VOLTAGE_COMP_SATURATION = 12.0;
    public static final double DRIVE_KS_VOLT = 0.667 / DRIVE_VOLTAGE_COMP_SATURATION;
    public static final double DRIVE_KV_VOLTPMPS = 2.44 / DRIVE_VOLTAGE_COMP_SATURATION;
    public static final double DRIVE_KA_VOLTPMPS_SQ = 0.27 / DRIVE_VOLTAGE_COMP_SATURATION;

    public static final double DRIVE_OPEN_LOOP_RAMP = 0.25;
    public static final double DRIVE_CLOSED_LOOP_RAMP = 0.0;

    public static final double DRIVE_CONTINUOUS_SUPPLY_CURRENT_LIMIT = 30;
    public static final double DRIVE_CONTINUOUS_STATOR_CURRENT_LIMIT = 80;
    public static final double DRIVE_PEAK_CURRENT_DURATION = 0.1;

    // TODO: PID probably to tune and change later! (pulled from frc2023)
    public static double DRIVE_PFAC = 0.10;
    public static double DRIVE_IFAC = 0.0;
    public static double DRIVE_DFAC = 0.0;
    public static double DRIVE_FFAC = 0.0;
  }

  /* OTHER */
  public static class Controllers { public static double XBOX_DRIVE_DEADBAND = 0.1; }
}
