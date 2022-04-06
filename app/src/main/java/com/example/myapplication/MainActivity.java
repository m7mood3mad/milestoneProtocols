package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    Thread qqq = null;
    EditText ip;
    EditText port;
    EditText msg;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip = (EditText) findViewById(R.id.ip);
        port = (EditText) findViewById(R.id.port);
        msg = (EditText) findViewById(R.id.msg);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Socket m = new Socket(ip.getText().toString(),Integer.parseInt(port.getText().toString()));
                            DataOutputStream dataOutputStream = new DataOutputStream(m.getOutputStream());
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(m.getInputStream()));
                            dataOutputStream.write((m.toString()+"\n").getBytes());
                            dataOutputStream.flush();
                            String s ="";
                            s = bufferedReader.readLine();
                            dataOutputStream.write("close connection".getBytes());
                            dataOutputStream.flush();
                            dataOutputStream.close();
                            m.close();
                        }
                        catch(NumberFormatException a){
                            a.printStackTrace();
                        }
                        catch(UnknownHostException a){
                            a.printStackTrace();
                        }
                        catch(IOException a){
                            a.printStackTrace();
                        }

                    }
                }).start();


            }
        });
    }
}