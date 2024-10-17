package com.example.multimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de ImageView para animación le hace el cuchau
        ImageView imageView = findViewById(R.id.imageView);

        // Configuración del empotrador de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        // Configuración del empotrador de video
        videoView = findViewById(R.id.myVideoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musicavideo);
        videoView.setVideoURI(videoUri);

        // Botón de reproducir warifaifa audio
        Button playAudioButton = findViewById(R.id.playAudioButton);
        playAudioButton.setOnClickListener(v -> mediaPlayer.start());

        // Botón de reproducir warifaifa video
        Button playVideoButton = findViewById(R.id.playVideoButton);
        playVideoButton.setOnClickListener(v -> videoView.start());

        // Botón de animación de imagen chuchau
        Button animateButton = findViewById(R.id.animateButton);
        animateButton.setOnClickListener(v -> {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
            imageView.startAnimation(fadeInAnimation);
        });

        // Botón para pausar la warifaifa
        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        });

        // Botón para reanudar la warifaifa
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
            if (!videoView.isPlaying()) {
                videoView.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

