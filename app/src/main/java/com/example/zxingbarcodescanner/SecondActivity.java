package com.example.zxingbarcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.Serializable;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

/**
 * Created by szidonia.laszlo on 2018. 03. 27..
 */

public class SecondActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {



    private static final int REQUEST_CAMERA = 1;


    private ZXingScannerView scannerView;

    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        scannerView = new ZXingScannerView(this);

        setContentView(scannerView);

    }





    @Override

    public void onResume() {

        super.onResume();


                if(scannerView == null) {

                    scannerView = new ZXingScannerView(this);

                    setContentView(scannerView);

                }

                scannerView.setResultHandler(this);

                scannerView.startCamera();


    }



    @Override

    public void onDestroy() {

        super.onDestroy();

        scannerView.stopCamera();

    }





    @Override

    public void handleResult(Result result) {

        final String myResult = result.getText();

        Log.d("QRCodeScanner", result.getText());

        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());



        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Scan Result");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                scannerView.resumeCameraPreview(SecondActivity.this);

            }

        });

        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));

                startActivity(browserIntent);

            }

        });

        builder.setMessage(result.getText());

        AlertDialog alert1 = builder.create();

        alert1.show();

    }

}