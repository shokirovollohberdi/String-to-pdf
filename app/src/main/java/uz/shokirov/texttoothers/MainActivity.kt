package uz.shokirov.texttoothers

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.Document
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import uz.shokirov.texttoothers.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var t = binding.edtText.getText()

        var testTitle = "this is title as test"


        binding.save.setOnClickListener {
            val path = getExternalFilesDir(null).toString() + "/" + "test_name" + ".pdf"
            val file = File(path)
            if (!file.exists()) {
                try {
                    file.createNewFile()
                } catch (e: Exception) {
                    Log.d(TAG, "onCreate: $e")
                }
            }

            val document = Document(PageSize.A4)

            try {
                PdfWriter.getInstance(document, FileOutputStream(file.absoluteFile))
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: $e")
            }
            document.open()

            try {
                document.add(Paragraph("crush"))
                document.add(Paragraph("\n"))
                document.add(Paragraph(t.toString()))
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: $e")
            }
            document.close()
        }


    }
}