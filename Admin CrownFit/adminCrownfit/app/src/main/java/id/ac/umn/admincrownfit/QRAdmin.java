package id.ac.umn.admincrownfit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRAdmin extends AppCompatActivity {
    EditText input_valueQR;
    Button createQR;
    ImageView QR;
    ImageView QRIntent;
    Button btnintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qradmin);
        input_valueQR = findViewById(R.id.input_valueQR);
        createQR = findViewById(R.id.createQR);
        QR = findViewById(R.id.QR);
        createQR.setOnClickListener(view -> {
            generateQR();
        });



    }

    private void generateQR(){
        String text = input_valueQR.getText().toString().trim();
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 800, 800);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            QR.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }



}