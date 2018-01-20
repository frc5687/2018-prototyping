package org.frc5687.powerup.prototyping.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.powerup.prototyping.RobotMap;

/**
 * Created by Ben Bernard on 1/14/2018.
 */
public class JeVoisProxy {

    private int lastReadX;
    private int lastReadY;

    private JeVoisListener listener;
    private Thread listenerThread;

    public JeVoisProxy() {
        listener = new JeVoisListener(this);
        listenerThread = new Thread(listener);
        listenerThread.start();
    }


    synchronized public int GetX() {
        return lastReadX;
    }

    synchronized public int GetY() {
        return lastReadY;
    }

    synchronized protected void Set(int x, int y) {
        lastReadX = x;
        lastReadY = y;
    }

    protected class JeVoisListener implements Runnable {
        private SerialPort jevoisPort;
        private JeVoisProxy proxy;

        protected JeVoisListener(JeVoisProxy proxy) {
            this.proxy = proxy;
            jevoisPort = new SerialPort(115200, SerialPort.Port.kMXP);
        }

        public void run() {
            int i = 0;
            while (true) {
                try {
                    /*
                    String data = "JeVoisTargetingInfo: 23;" + Integer.toString(i);//
                    i++;
                    */
                    String data = jevoisPort.readString();
                    if (data.length() == 0) {
                        continue;
                    }
                    String identifier_string = "JeVoisTargetingInfo: ";
                    // Check to see if the identifier_string prepends our payload
                    if (data.length() <= identifier_string.length() || !data.substring(0, identifier_string.length()).equals(identifier_string)) {
                        DriverStation.reportError(String.format("Rejected %s", data), false);
                        continue;
                    }
                    String payload = data.substring(identifier_string.length(), data.length());
                    //DriverStation.reportError(data, false);
                    //DriverStation.reportError(data.substring(0, identifier_string.length()), false);
                    //DriverStation.reportError(payload, false);
                    /*
                    if (!data.substring(0, identifier_string.length()).equals(identifier_string)) {
                        break;
                    }
                    data = data.substring(identifier_string.length(), data.length());
                    */
                    String[] a = payload.split(";");
                    int x = Integer.parseInt(a[0]);
                    int y = Integer.parseInt(a[1]);
                    // DriverStation.reportError(String.format("x: %s; y: %s", x, y), false);
                    proxy.Set(x, y);
                    Thread.sleep(20);
                } catch (Exception e) {
                    continue;
                    // DriverStation.reportError(e.toString(), true);
                }
            }
        }
    }
}
