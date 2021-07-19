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
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

 public class MainActivity extends AppCompatActivity {

     class BluetoothProfileController extends BluetoothHidDevice.Callback implements BluetoothProfile.ServiceListener {
        private static final String TAG = "BluetoothProfileController";

        /* HID service */
        private BluetoothHidDeviceAppSdpSettings sdpSettings =
                new BluetoothHidDeviceAppSdpSettings("InokiHID", "Inoki's HID profile tester", "Inoki",
                        BluetoothHidDevice.SUBCLASS1_COMBO,
                        Descriptors.MOUSE_KEYBOARD_COMBO);

        private BluetoothHidDeviceAppQosSettings qosSettings = new BluetoothHidDeviceAppQosSettings(
                BluetoothHidDeviceAppQosSettings.SERVICE_BEST_EFFORT, 800, 9, 0, 11250, BluetoothHidDeviceAppQosSettings.MAX);

        private BluetoothHidDevice hidDevice;

        @Override
        public void onServiceConnected(int profile, BluetoothProfile bluetoothProfile) {
            Log.d(TAG, "Service connected!");
            if (profile == BluetoothProfile.HID_DEVICE) {
                if (bluetoothProfile instanceof BluetoothHidDevice) {
                    // Store it in class
                    hidDevice = (BluetoothHidDevice) bluetoothProfile;
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
            if (profile == BluetoothProfile.HID_DEVICE)
                hidDevice = null;
        }

        /* Constructor */
        public BluetoothProfileController() {
            super();
            // Get the profile proxy, only listen on HID
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(MainActivity.this, this, BluetoothProfile.HID_DEVICE);
        }

        private BluetoothDevice hostDevice = null;

        public class MouseReportSender {
            public MouseReport mouseReport = new MouseReport();

            protected void sendMouse() {
                if (!hidDevice.sendReport(hostDevice, MouseReport.ID, mouseReport.getReport())) {
                    Log.e(TAG, "Report wasn't sent");
                } else {
                    Log.i(TAG, (int)mouseReport.getReport()[0] + " sent");
                }
            }

            public void sendLeftClickOn() {
                mouseReport.setLeftButton(true);
                sendMouse();
            }
            public void sendLeftClickOff() {
                mouseReport.setLeftButton(false);
                sendMouse();
            }
        }

        /* Callback events */
        public void onAppStatusChanged(BluetoothDevice pluggedDevice, boolean registered) {
            super.onAppStatusChanged(pluggedDevice, registered);
            Log.i(TAG, "onAppStatusChanged");

            if (registered) {
                List<BluetoothDevice> pairedDevices = hidDevice.getDevicesMatchingConnectionStates(new int[] {
                        BluetoothProfile.STATE_CONNECTING,
                        BluetoothProfile.STATE_CONNECTED,
                        BluetoothProfile.STATE_DISCONNECTED,
                        BluetoothProfile.STATE_DISCONNECTING
                });
                if (pairedDevices.size() > 0) {
                    Log.d("paired d", "paired devices found");
                    BluetoothDevice firstPairedDevice = pairedDevices.get(0);

                    if (hidDevice.getConnectionState(pluggedDevice) == BluetoothProfile.STATE_DISCONNECTED && pluggedDevice != null) {
                        hidDevice.connect(pluggedDevice);
                        mouseSender = new MouseReportSender();
                    } else if (hidDevice.getConnectionState(firstPairedDevice) == BluetoothProfile.STATE_DISCONNECTED) {
                        hidDevice.connect(firstPairedDevice);
                        mouseSender = new MouseReportSender();
                    }
                } else {
                    Log.i("error", "No paired device");
                }
            } else {
                Log.i(TAG, "Not registered");
            }
        }

        public void onConnectionStateChanged(BluetoothDevice device, int state) {
            Log.i(TAG, "onConnectionStateChanged");
            if (state == BluetoothProfile.STATE_CONNECTED) {
                if (device != null) {
                    hostDevice = device;
                    Log.e(TAG, "Device connected");
                    // TODO: Callback for connection
                } else {
                    Log.e(TAG, "Device not connected");
                }
            } else {
                hostDevice = null;
                if(state == BluetoothProfile.STATE_DISCONNECTED)
                {
                    // TODO: Callback for disconnection
                }
            }
        }

        public void onGetReport(BluetoothDevice device, byte type, byte id, int bufferSize) {
            super.onGetReport(device, type, id, bufferSize);
            Log.i(TAG, "onGetReport");

            if (type == BluetoothHidDevice.REPORT_TYPE_FEATURE) {
                Log.i(TAG, "Feature report received");
                /*
                featureReport.wheelResolutionMultiplier = true
                featureReport.acPanResolutionMultiplier = true
                Log.i("getbthid","$btHid")

                var wasrs=btHid?.replyReport(device, type, FeatureReport.ID, featureReport.bytes)
                Log.i("replysuccess flag ",wasrs.toString())
                */
            }
        }

        public void onSetReport(BluetoothDevice device, byte type, byte id, byte[] data) {
            super.onSetReport(device, type, id, data);
            Log.i(TAG, "onSetReport");
        }
     }

     private BluetoothProfileController controller;
     private BluetoothProfileController.MouseReportSender mouseSender;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         if (controller == null) {
             if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                 controller = new BluetoothProfileController();

                 ((Button)findViewById(R.id.left_button)).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (mouseSender != null) {
                             mouseSender.sendLeftClickOn();
                             try {
                                 Thread.sleep(10);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                             mouseSender.sendLeftClickOff();
                         } else {
                             Log.i("Test", "No device connected");
                         }
                     }
                 });
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