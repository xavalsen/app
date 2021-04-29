package com.example.icl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.icl.R;

import java.io.IOException;

public class Bienvenido extends AppCompatActivity {

    Button btnLogin, btnRegistro;
    VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        video = (VideoView) findViewById(R.id.video);

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.videoplayback);

        video.setVideoURI(uri);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.setLooping(true);
            }
        });

        video.start();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent1, 0);
            }
        });

        Button btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (v.getContext(), Registro.class);
                startActivityForResult(intent1, 0);
            }
        });
    }


}