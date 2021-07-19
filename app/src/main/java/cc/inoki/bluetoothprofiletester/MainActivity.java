 package cc.inoki.bluetoothprofiletester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothHidDeviceAppQosSettings;
import android.bluetooth.BluetoothHidDeviceAppSdpSettings;
import android.bluetooth.BluetoothProfile;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

 public class MainActivity extends AppCompatActivity {

    class BluetoothProfileController extends BluetoothHidDevice.Callback implements BluetoothProfile.ServiceListener {
        private static final String TAG = "BluetoothProfileController";

        /* HID service */
        private BluetoothHidDeviceAppSdpSettings sdpSettings =
                new BluetoothHidDeviceAppSdpSettings("InokiHID", "Inoki's HID profile tester", "Inoki",
                        BluetoothHidDevice.SUBCLASS1_COMBO, new byte[] {0x05, 0x01, 0x09, 0x02});

        private BluetoothHidDeviceAppQosSettings qosSettings = new BluetoothHidDeviceAppQosSettings(
                BluetoothHidDeviceAppQosSettings.SERVICE_BEST_EFFORT, 800, 9, 0, 11250, BluetoothHidDeviceAppQosSettings.MAX);
        @Override
        public void onServiceConnected(int profile, BluetoothProfile bluetoothProfile) {
            Log.d(TAG, "Service connected!");
            if (profile == BluetoothProfile.HID_DEVICE) {
                if (bluetoothProfile instanceof BluetoothHidDevice) {
                    // TODO: store it in class
                    BluetoothHidDevice hidDevice = (BluetoothHidDevice) bluetoothProfile;
                    hidDevice.registerApp(sdpSettings, null, qosSettings, new Executor() {
                        @Override
                        public void execute(Runnable runnable) {
                            runnable.run();
                        }
                    }, this);
                    // BluetoothHidDeviceAppSdpSettings sdp, BluetoothHidDeviceAppQosSettings inQos, BluetoothHidDeviceAppQosSettings outQos,  executor, BluetoothHidDevice.Callback callback
                    Log.d(TAG, "Start Discovery!");
                    BluetoothAdapter.getDefaultAdapter().startDiscovery();
                }
            }
        }

        @Override
        public void onServiceDisconnected(int profile) {
            Log.d(TAG, "Disconnected to service");
        }

        /* Constructor */
        public BluetoothProfileController() {
            super();
            // Get the profile proxy, only listen on HID
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(MainActivity.this, this, BluetoothProfile.HID_DEVICE);
        }

        /* Callback events */
        public void onAppStatusChanged(BluetoothDevice pluggedDevice, boolean registered) {
            super.onAppStatusChanged(pluggedDevice, registered);
            Log.i(TAG, "onAppStatusChanged");
        }

        public void onConnectionStateChanged(BluetoothDevice device, int state) {
            Log.i(TAG, "onConnectionStateChanged");
        }

        public void onGetReport(BluetoothDevice device, byte type, byte id, int bufferSize) {
            super.onGetReport(device, type, id, bufferSize);
            Log.i(TAG, "onGetReport");
        }

        public void onSetReport(BluetoothDevice device, byte type, byte id, byte[] data) {
            super.onSetReport(device, type, id, data);
            Log.i(TAG, "onSetReport");
        }

//        public void onSetProtocol(BluetoothDevice device, byte protocol) {
//            super.onSetReport(device, type, id, data);
//            Log.i(TAG, "onSetReport");
//        }

//        public void onInterruptData(BluetoothDevice device, byte reportId, byte[] data) {
//            throw new RuntimeException("Stub!");
//        }

//        public void onVirtualCableUnplug(BluetoothDevice device) {
//            throw new RuntimeException("Stub!");
//        }

    }

    private BluetoothProfileController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (controller == null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                controller = new BluetoothProfileController();
            } else {
                Log.i("Main", "Cannot create controller due to restricted permission");
                requestPermissions(
                        new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, 0
                );
            }
        }
    }

     @Override
     protected void onStop() {
         super.onStop();
     }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (controller == null) {
                controller = new BluetoothProfileController();
            }
         }
     }
 }