package com.gzeinnumer.externalappenttextfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.gzeinnumer.externalappenttextfile.helper.FunctionGlobalDir;
import com.gzeinnumer.externalappenttextfile.helper.FunctionGlobalFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_";

    TextView tv;
    String msg="externalappenttextfile\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TAG);

        tv = findViewById(R.id.tv);

        if (FunctionGlobalDir.isFileExists(FunctionGlobalDir.appFolder+"/File.txt")){
            if(FunctionGlobalFile.appentText(FunctionGlobalDir.appFolder+"/File.txt", "Pesan yang dikirim")){
                msg+="Line baru ditambah ke file ";
                tv.setText(msg);
            } else {
                msg+="Ada error ketika add pesan";
                tv.setText(msg);
            }
        } else {
            if(FunctionGlobalFile.initFile("Text yang dibuat di file")){
                List<String> list = FunctionGlobalFile.readFile(FunctionGlobalDir.appFolder+"/File.txt");
                if (list !=null){
                    msg+="Jumlah line di file ada "+ list.size();
                    tv.setText(msg);
                } else {
                    msg+="Gagal membaca data";
                    tv.setText(msg);
                }
            } else {
                msg+="File gagal dibuat ";
                tv.setText(msg);
            }
        }
    }
}