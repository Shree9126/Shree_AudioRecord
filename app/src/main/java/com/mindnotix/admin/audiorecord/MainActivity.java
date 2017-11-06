package com.mindnotix.admin.audiorecord;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;


public class MainActivity extends AppCompatActivity {


    Button button2;
    // Create a media file name

    // For unique file name appending current timeStamp with file name


    private static final int REQUEST_RECORD_AUDIO = 0;
    private static  String AUDIO_FILE_PATH1 =
            Environment.getExternalStorageDirectory().getPath() + "/recordshree_"+getname()+"audio.wav";

    private static  String AUDIO_FILE_PATH ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 =(Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent =new Intent(MainActivity.this,sample.class);
                startActivity(mIntent);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)));
        }

        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


public static String   getname(){
        java.util.Date date = new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());

    return timeStamp;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
                System.out.println("Path uri="+AUDIO_FILE_PATH);
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
            else{
                System.out.println("Path uri failed="+AUDIO_FILE_PATH);
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recordAudio(View v) {

    //    AUDIO_FILE_PATH =Environment.getExternalStorageDirectory().getPath() + "/recordshree_"+getname()+"audio.wav";

        File file = getOutputMediaFile();
        AUDIO_FILE_PATH =file.getPath() + "/AUD_"+getname()+".wav";

        Log.d("path",AUDIO_FILE_PATH);
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(AUDIO_FILE_PATH)
                .setColor(ContextCompat.getColor(this, R.color.recorder_bg))
                .setRequestCode(REQUEST_RECORD_AUDIO)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(false)
                .setKeepDisplayOn(true)

                // Start recording
                .record();
    }




    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile() {

           // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(

                Environment.DIRECTORY_MUSIC), "MyVoiceKspots");

        // Create the storage directory(MyCameraVideo) if it does not exist
        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {

                Log.d("MyVoiceKspot", "Failed to create directory MyVoiceKspot.");

                Toast.makeText(this, "Failed to create directory MyVoiceKspot.",
                        Toast.LENGTH_LONG).show();

                Log.d("MyVoiceKspot", "Failed to create directory MyVoiceKspot.");
                return null;
            }
        }

        return mediaStorageDir;
    }

}