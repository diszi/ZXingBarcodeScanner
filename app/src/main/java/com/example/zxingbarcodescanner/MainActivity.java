package com.example.zxingbarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tvStatus, tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.butOther);
        final Activity activity = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
//               intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            //   intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
           //     intentIntegrator.setPrompt("Scan");
              //  intentIntegrator.setCameraId(0);
              //  intentIntegrator.setBeepEnabled(false);
             //   intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
            }
        });

    }




    public void onActivityResult(int requestCode, int resultCode, Intent intent) {




        System.out.println(" requestCode = "+requestCode+" resultCode = "+resultCode+" intent = "+intent);


        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if (intentResult != null){
            if (intentResult.getContents()  == null){
                Toast.makeText(getApplicationContext(),"You cancelled the scanning",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),intentResult.getContents(),Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,intent);
        }


    }

}
