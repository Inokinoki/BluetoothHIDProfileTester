package cc.inoki.bluetoothprofiletester;

public class MouseReport {
    public byte[] getReport() {
        return report;
    }

    private byte []report = new byte[7];
    public static final int ID = 4;

    public short getDy() {
        return (short)((short)report[3] | ((short)report[4] << 8));
    }

    public void setDy(short dy) {
        report[3] = (byte)(dy & 0xFF);
        report[4] = (byte)((dy >> 8) & 0xFF);
    }

    public short getDx() {
        return (short)((short)report[1] | ((short)report[2] << 8));
    }

    public void setDx(short dx) {
        report[1] = (byte)(dx & 0xFF);
        report[2] = (byte)((dx >> 8) & 0xFF);
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

    public byte getVscroll() {
        return report[6];
    }

    public void setVscroll(byte vscroll) {
        report[5] = vscroll;
    }

    public byte getHscroll() {
        return report[6];
    }

    public void setHscroll(byte hscroll) {
        report[6] = hscroll;
    }

    public void reset() {
        report[0] = 0;
        report[1] = 0;
        report[2] = 0;
        report[3] = 0;
        report[4] = 0;
        report[5] = 0;
        report[6] = 0;
    }
}
