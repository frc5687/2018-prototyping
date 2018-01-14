package org.frc5687.powerup.prototyping.utils;

import edu.wpi.first.wpilibj.SerialPort;

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
            jevoisPort = new SerialPort(15200, SerialPort.Port.kOnboard);
        }

        public void run() {
            while (true) {
                try {
                    String data = jevoisPort.readString();
                    String[] a = data.split(";");
                    int x = Integer.parseInt(a[0]);
                    int y = Integer.parseInt(a[1]);
                    proxy.Set(x, y);

                    Thread.sleep(20);
                } catch (Exception e) {
                    break;
                }
            }
        }
    }
}
