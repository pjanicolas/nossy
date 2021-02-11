package com.example.nossy;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import java.util.HashMap;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    HashMap<String, Integer> aNoiseImageMap = new HashMap<String, Integer>();
    HashMap<String, Boolean> aActiveMap = new HashMap<String, Boolean>();
    HashMap<String, Integer > aNoiseSoundMap = new HashMap<String, Integer>();
    HashMap<String, Integer > aStreamIdMap = new HashMap<String, Integer>();
    SoundPool oSoundPool;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initializeVariables();
    }

    public void imageClick(View v) {
        String sId = getResources().getResourceEntryName(v.getId());
        ImageView oImage = (ImageView) findViewById(v.getId());
        Boolean bIsActive = aActiveMap.get(sId);
        if (bIsActive != null && bIsActive) {
            aActiveMap.put(sId, false);
            oImage.setImageAlpha(150);
            stopSound(sId);
        } else if (bIsActive != null) {
            aActiveMap.put(sId, true);
            oImage.setImageAlpha(255);
            playSound(sId);
        }
    }

    private void playSound(String sId)
    {
        int iSoundMap = aNoiseSoundMap.get(sId);
        int iStreamId = oSoundPool.play(iSoundMap, 1.0f, 1.0f, 1, -1, 1.0f);
        if (iStreamId == 0) {
            playSound(sId);
        } else {
            aStreamIdMap.put(sId, iStreamId);
        }
    }

    private void stopSound(String sId)
    {
        int iStreamId = aStreamIdMap.get(sId);
        oSoundPool.stop(iStreamId);
        aStreamIdMap.remove(sId);
    }

    private void initializeVariables()
    {
        initializeActiveMap();
        initializeSoundMap();
        aNoiseImageMap.put("bird", R.id.bird);
        aNoiseImageMap.put("cave", R.id.cave);
        aNoiseImageMap.put("city", R.id.city);
        aNoiseImageMap.put("coffeshop", R.id.coffeshop);
        aNoiseImageMap.put("dessertwind", R.id.dessertwind);
        aNoiseImageMap.put("fire", R.id.fire);
        aNoiseImageMap.put("lightrain", R.id.lightrain);
        aNoiseImageMap.put("lightwind", R.id.lightwind);
        aNoiseImageMap.put("morning", R.id.morning);
        aNoiseImageMap.put("night", R.id.night);
        aNoiseImageMap.put("thunder", R.id.thunder);
        aNoiseImageMap.put("train", R.id.train);
        aNoiseImageMap.put("waterdrop", R.id.waterdrop);
        aNoiseImageMap.put("waves", R.id.waves);
        for (Integer iId : aNoiseImageMap.values()) {
            ImageView oImage = (ImageView) findViewById(iId);
            oImage.setImageAlpha(150);
        }
    }

    private void initializeActiveMap()
    {
        aActiveMap.put("bird", false);
        aActiveMap.put("cave", false);
        aActiveMap.put("city", false);
        aActiveMap.put("coffeshop", false);
        aActiveMap.put("dessertwind", false);
        aActiveMap.put("fire", false);
        aActiveMap.put("lightrain", false);
        aActiveMap.put("lightwind", false);
        aActiveMap.put("morning", false);
        aActiveMap.put("night", false);
        aActiveMap.put("thunder", false);
        aActiveMap.put("train", false);
        aActiveMap.put("waterdrop", false);
        aActiveMap.put("waves", false);
    }

    private void initializeSoundMap()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            oSoundPool = new SoundPool.Builder()
                    .setMaxStreams(14)
                    .build();
        } else {
            oSoundPool = new SoundPool(14, AudioManager.STREAM_MUSIC, 0);
        }
        aNoiseSoundMap.put("bird", oSoundPool.load(getApplicationContext(), R.raw.bird, 1));
        aNoiseSoundMap.put("cave", oSoundPool.load(getApplicationContext(), R.raw.cave, 1));
        aNoiseSoundMap.put("city", oSoundPool.load(getApplicationContext(), R.raw.city, 1));
        aNoiseSoundMap.put("coffeshop", oSoundPool.load(getApplicationContext(), R.raw.coffeeshop, 1));
        aNoiseSoundMap.put("dessertwind", oSoundPool.load(getApplicationContext(), R.raw.desertwind, 1));
        aNoiseSoundMap.put("fire", oSoundPool.load(getApplicationContext(), R.raw.fire, 1));
        aNoiseSoundMap.put("lightrain", oSoundPool.load(getApplicationContext(), R.raw.lightrain, 1));
        aNoiseSoundMap.put("lightwind", oSoundPool.load(getApplicationContext(), R.raw.lightwind, 1));
        aNoiseSoundMap.put("morning", oSoundPool.load(getApplicationContext(), R.raw.morning, 1));
        aNoiseSoundMap.put("night", oSoundPool.load(getApplicationContext(), R.raw.night, 1));
        aNoiseSoundMap.put("thunder", oSoundPool.load(getApplicationContext(), R.raw.thunder, 1));
        aNoiseSoundMap.put("train", oSoundPool.load(getApplicationContext(), R.raw.train, 1));
        aNoiseSoundMap.put("waterdrop", oSoundPool.load(getApplicationContext(), R.raw.waterdrop, 1));
        aNoiseSoundMap.put("waves", oSoundPool.load(getApplicationContext(), R.raw.waves, 1));
    }
}
