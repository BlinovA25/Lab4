package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
//доп. библиотеки
import java.io.*;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView T = (TextView) findViewById(R.id.textView);
        boolean rc = false;
        File f = new File(super.getFilesDir(), "Base_Lab.txt");
        if(rc = f.exists())
            T.setText("Псс, парень, файл существует");
        else
            T.setText("Обломчик, файлика то нету");
    }

    public void SaveInfo(View view) {
        TextView T1 = (TextView) findViewById(R.id.SurnameT);
        TextView T2 = (TextView) findViewById(R.id.NameT);

        String name = String.valueOf(T1.getText());
        String surname = String.valueOf(T2.getText());

        WriteToFile(name, surname);
        T1.setText("");
        T2.setText("");
    }


    public void WriteToFile(String n, String s){
        String WritebleText = n + " " + s + "\r\n";
        try {
            FileOutputStream fileOutput = openFileOutput("Base_Lab.txt",MODE_APPEND);
            fileOutput.write(WritebleText.getBytes());
            fileOutput.close();
            /*Toast toast= Toast.makeText(MainActivity.this,"Текст сохранен",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,0);
            toast.setGravity(Gravity.RIGHT, 200,0);
            toast.show();*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadFromFile(View view){
        TextView T = (TextView) findViewById(R.id.textView2);
        try {
            FileInputStream fileInput =openFileInput("Base_Lab.txt");
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            // cчитанные строки
            StringBuffer strBuffer = new StringBuffer();
            String lines;
            while ((lines=buffer.readLine())!= null){
                strBuffer.append(lines + "\n");
            }
            T.setText(strBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean ExistFile(String FileName)
    {
        boolean rc = false;
        File f = new File(super.getFilesDir(), FileName);
        if(rc = f.exists()) Log.d("Log_02", "Файл "+ FileName + " существует!");
        else
            {
                Log.d("Log_02", "Файл "+ FileName + " не найден, но сейчас будет создан!");
                try {
                    f.createNewFile();
                    Log.d("Log_02", "Файл "+ FileName + " создан!");
                }
                catch (IOException e)
                {Log.d("Log_02", "Файл "+ FileName + " не открыт");}
            }

        return rc;
    }

    public void ClearFile()
    {
        TextView T = findViewById(R.id.textView2);
        /*Toast toast= Toast.makeText(MainActivity.this,"Ты зачем нажал сюда?",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0,0);
            toast.setGravity(Gravity.RIGHT, 0,0);
            toast.show();*/
    }




}