package cc.inoki.bluetoothprofiletester;

public class MouseReport {
    public byte[] getReport() {
        return report;
    }

    private byte []report = new byte[7];
    public static final int ID = 4;

    public byte getDy() {
        return report[2];
    }

    public void setDy(byte dy) {
        report[2] = dy;
    }

    public byte getDx() {
        return report[1];
    }

    public void setDx(byte dx) {
        report[1] = dx;
    }

    public boolean getLeftButton() {
        return (report[0] & 0b01) != 0;
    }

    public void setLeftButton(boolean leftButton) {
        report[0] = leftButton ? (byte)(report[0] | 0b01) : (byte)(report[0] & 0b110);
    }

    public boolean getRightButton() {
        return (report[0] & 0b10) != 0;
    }

    public void setRightButton(boolean rightButton) {
        report[0] = rightButton ? (byte)(report[0] | 0b10) : (byte)(report[0] & 0b101);
    }

    public void reset() {
        report[0] = 0;
        report[1] = 0;
        report[2] = 0;
        report[3] = 0;
        report[4] = 0;
        report[5] = 0;
        report[6] = 0;
        report[7] = 0;
    }
}
