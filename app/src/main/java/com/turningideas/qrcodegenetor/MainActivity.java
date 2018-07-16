package com.turningideas.qrcodegenetor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private Button btnGenerate;
    private ImageView ivQR;
    private String textQr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivQR = findViewById(R.id.ivQR);
        btnGenerate = findViewById(R.id.btnGenerate);
        etAddress = findViewById(R.id.etAddress);
        etName = findViewById(R.id.etName);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textQr=etName.getText().toString().trim()+";"+etAddress.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(textQr, BarcodeFormat.QR_CODE, 500, 500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivQR.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
