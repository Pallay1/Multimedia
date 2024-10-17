package com.example.multimedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private MediaPlayer mediaPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verificar y solicitar permisos
        checkPermissions();

        // Configuración de ImageView para animación
        ImageView imageView = findViewById(R.id.imageView);

        // Configuración del reproductor de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        // Configuración del reproductor de video
        videoView = findViewById(R.id.myVideoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musicavideo);
        videoView.setVideoURI(videoUri);

        // Botón de reproducir audio
        Button playAudioButton = findViewById(R.id.playAudioButton);
        playAudioButton.setOnClickListener(v -> mediaPlayer.start());

        // Botón de reproducir video
        Button playVideoButton = findViewById(R.id.playVideoButton);
        playVideoButton.setOnClickListener(v -> videoView.start());

        // Botón de animación de imagen
        Button animateButton = findViewById(R.id.animateButton);
        animateButton.setOnClickListener(v -> {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
            imageView.startAnimation(fadeInAnimation);
        });

        // Botón para pausar audio y video
        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        });

        // Botón para reanudar audio y video
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

    // Método para verificar y solicitar permisos
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
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
