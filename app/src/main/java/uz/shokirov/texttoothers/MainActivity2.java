package uz.shokirov.texttoothers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    String matn;
    EditText text;
    Button save;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = findViewById(R.id.edtText);
        save = findViewById(R.id.btn);
        String title;
        title = "save";
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HHmmss").format(Calendar.getInstance().getTime());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = getExternalFilesDir(null).toString() + "/" + title + ".pdf";
                matn = text.toString();
                File file = new File(path);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        Log.d(TAG, "onClick: " + e.toString());
                    }
                }

                Document document = new Document(PageSize.A4);

                try {
                    PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

                } catch (Exception e) {
                    Log.d(TAG, "onClick: " + e.toString());
                }
                document.open();

                try {

                    document.add(new Paragraph(title));
                    document.add(new Paragraph("\n"));
                    document.add(new Paragraph(matn));


                } catch (Exception e) {
                    Log.d(TAG, "onClick: " + e.toString());
                }
                document.close();

            }
        });


    }
}