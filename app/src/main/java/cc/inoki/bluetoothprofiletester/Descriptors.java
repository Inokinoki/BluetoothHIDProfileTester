package cc.inoki.bluetoothprofiletester;

public class Descriptors {
    public static final byte MOUSE_KEYBOARD_COMBO[] = new byte[]{
            // MOUSE TLC
            (byte) 0x05, (byte) 0x01,   // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x02,   // USAGE (Mouse)

            (byte) 0xa1, (byte) 0x01,   // COLLECTION (Application)
            (byte) 0x05, (byte) 0x01,   // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x02,   // USAGE (Mouse)
            (byte) 0xa1, (byte) 0x02,   // COLLECTION (Logical)

            (byte) 0x85, (byte) 0x04,   // REPORT_ID (Mouse)
            (byte) 0x09, (byte) 0x01,   // USAGE (Pointer)
            (byte) 0xa1, (byte) 0x00,   // COLLECTION (Physical)
            (byte) 0x05, (byte) 0x09,   // USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x01,   // USAGE_MINIMUM (Button 1)
            (byte) 0x29, (byte) 0x02,   // USAGE_MAXIMUM (Button 2)
            (byte) 0x15, (byte) 0x00,   // LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,   // LOGICAL_MAXIMUM (1)
            (byte) 0x75, (byte) 0x01,   // REPORT_SIZE (1)
            (byte) 0x95, (byte) 0x02,   // REPORT_COUNT (2)
            (byte) 0x81, (byte) 0x02,   // INPUT (Data,Var,Abs)
            (byte) 0x95, (byte) 0x01,   // REPORT_COUNT (1)
            (byte) 0x75, (byte) 0x06,   // REPORT_SIZE (6)
            (byte) 0x81, (byte) 0x03,   // INPUT (Cnst,Var,Abs)
            (byte) 0x05, (byte) 0x01,   // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x30,   // USAGE (X)
            (byte) 0x09, (byte) 0x31,   // USAGE (Y)
            (byte) 0x16, (byte) 0x01, (byte) 0xf8,  // LOGICAL_MINIMUM (-2047)
            (byte) 0x26, (byte) 0xff, (byte) 0x07,  // LOGICAL_MAXIMUM (2047)
            (byte) 0x75, (byte) 0x10,   // REPORT_SIZE (16)
            (byte) 0x95, (byte) 0x02,   // EEPORT_COUNT (2)
            (byte) 0x81, (byte) 0x06,   // INPUT (Data,Var,Rel)

            (byte) 0xa1, (byte) 0x02,   // COLLECTION (Logical)
            (byte) 0x85, (byte) 0x06,   // REPORT_ID (Feature)
            (byte) 0x09, (byte) 0x48,   // USAGE (Resolution Multiplier)

            (byte) 0x15, (byte) 0x00,   // LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,   // LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x01,   // PHYSICAL_MINIMUM (1)
            (byte) 0x45, (byte) 0x04,   // PHYSICAL_MAXIMUM (4)
            (byte) 0x75, (byte) 0x02,   // REPORT_SIZE (2)
            (byte) 0x95, (byte) 0x01,   // REPORT_COUNT (1)

            (byte) 0xb1, (byte) 0x02,   // FEATURE (Data,Var,Abs)


            (byte) 0x85, (byte) 0x04,   // REPORT_ID (Mouse)
            //(byte)0x05, (byte)0x01,   // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x38,   // USAGE (Wheel)

            (byte) 0x15, (byte) 0x81,   // LOGICAL_MINIMUM (-127)
            (byte) 0x25, (byte) 0x7f,   // LOGICAL_MAXIMUM (127)
            (byte) 0x35, (byte) 0x00,   // PHYSICAL_MINIMUM (0) - reset physical
            (byte) 0x45, (byte) 0x00,   // PHYSICAL_MAXIMUM (0)
            (byte) 0x75, (byte) 0x08,   // REPORT_SIZE (8)
            (byte) 0x95, (byte) 0x01,   // REPORT_COUNT (1)
            (byte) 0x81, (byte) 0x06,   // INPUT (Data,Var,Rel)
            (byte) 0xc0,                // END_COLLECTION

            (byte) 0xa1, (byte) 0x02,   // COLLECTION (Logical)
            (byte) 0x85, (byte) 0x06,   // REPORT_ID (Feature)
            (byte) 0x09, (byte) 0x48,   // USAGE (Resolution Multiplier)

            (byte) 0x15, (byte) 0x00,   // LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,   // LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x01,   // PHYSICAL_MINIMUM (1)
            (byte) 0x45, (byte) 0x04,   // PHYSICAL_MAXIMUM (4)
            (byte) 0x75, (byte) 0x02,   // REPORT_SIZE (2)
            (byte) 0x95, (byte) 0x01,   // REPORT_COUNT (1)

            (byte) 0xb1, (byte) 0x02,   // FEATURE (Data,Var,Abs)

            (byte) 0x35, (byte) 0x00,   // PHYSICAL_MINIMUM (0) - reset physical
            (byte) 0x45, (byte) 0x00,   // PHYSICAL_MAXIMUM (0)
            (byte) 0x75, (byte) 0x04,   // REPORT_SIZE (4)
            (byte) 0xb1, (byte) 0x03,   // FEATURE (Cnst,Var,Abs)


            (byte) 0x85, (byte) 0x04,   // REPORT_ID (Mouse)
            (byte) 0x05, (byte) 0x0c,   // USAGE_PAGE (Consumer Devices)
            (byte) 0x0a, (byte) 0x38, (byte) 0x02,   // USAGE (AC Pan)

            (byte) 0x15, (byte) 0x81,   // LOGICAL_MINIMUM (-127)
            (byte) 0x25, (byte) 0x7f,   // LOGICAL_MAXIMUM (127)
            (byte) 0x75, (byte) 0x08,   // REPORT_SIZE (8)
            (byte) 0x95, (byte) 0x01,   // REPORT_COUNT (1)
            (byte) 0x81, (byte) 0x06,   // INPUT (Data,Var,Rel)
            (byte) 0xc0,                // END_COLLECTION
            (byte) 0xc0,                // END_COLLECTION

            (byte) 0xc0,                // END_COLLECTION
            (byte) 0xc0,                // END_COLLECTION

            (byte) 0x05, (byte) 0x01,   // USAGE_PAGE (Generic Desktop)

            (byte) 0x09, (byte) 0x06,   // Usage (Keyboard)
            (byte) 0xA1, (byte) 0x01,   // Collection (Application)
            (byte) 0x85, (byte) 0x08,   // REPORT_ID (Keyboard)
            (byte) 0x05, (byte) 0x07,   // Usage Page (Key Codes)
            (byte) 0x19, (byte) 0xe0,   // Usage Minimum (224)
            (byte) 0x29, (byte) 0xe7,   // Usage Maximum (231)
            (byte) 0x15, (byte) 0x00,   // Logical Minimum (0)
            (byte) 0x25, (byte) 0x01,   // Logical Maximum (1)
            (byte) 0x75, (byte) 0x01,   // Report Size (1)
            (byte) 0x95, (byte) 0x08,   // Report Count (8)
            (byte) 0x81, (byte) 0x02,   // Input (Data, Variable, Absolute)

            (byte) 0x95, (byte) 0x01,   // Report Count (1)
            (byte) 0x75, (byte) 0x08,   // Report Size (8)
            (byte) 0x81, (byte) 0x01,   // Input (Constant) reserved byte(1)


            (byte) 0x95, (byte) 0x01,   // Report Count (1)
            (byte) 0x75, (byte) 0x08,   // Report Size (8)
            (byte) 0x15, (byte) 0x00,   // Logical Minimum (0)
            (byte) 0x25, (byte) 0x65,   // Logical Maximum (101)
            (byte) 0x05, (byte) 0x07,   // Usage Page (Key codes)
            (byte) 0x19, (byte) 0x00,   // Usage Minimum (0)
            (byte) 0x29, (byte) 0x65,   // Usage Maximum (101)
            (byte) 0x81, (byte) 0x00,   // Input (Data, Array) Key array(6 bytes)
            (byte) 0xc0                 // End Collection (Application)
    };
}
