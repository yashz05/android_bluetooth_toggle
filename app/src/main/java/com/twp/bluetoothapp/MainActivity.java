package com.twp.bluetoothapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int REQUEST_ENABLE_BT = 0;
        final int REQUEST_DISCOVERABLE_BT = 0;
        Switch bt = findViewById(R.id.switch1);
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {

                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
                                return;
                            }
                        }

                        return;
                    }
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }else{
                    bluetoothAdapter.disable();
                }
            }
        });
    }
}