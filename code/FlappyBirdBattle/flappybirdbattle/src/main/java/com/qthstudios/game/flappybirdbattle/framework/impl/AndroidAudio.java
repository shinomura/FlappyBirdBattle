package com.qthstudios.game.flappybirdbattle.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import com.qthstudios.game.flappybirdbattle.framework.signature.Audio;
import com.qthstudios.game.flappybirdbattle.framework.signature.Music;

public class AndroidAudio implements Audio {
    // use to manage resource
    AssetManager assets;

    // use to manage environment sounds (bullet, character ...)
    // Notes : The SoundPool.load() method executes the actual loading asynchronously.
    // This means that we should wait for a little bit before SoundPool.play()with that sound effect,
    // as the loading might not be finished yet.
    // Becareful : there’s no way to check when the sound effect is done loading.
    // That’s only possible with the SDK version 8 of SoundPool, use to support all Android versions.
    // Usually it’s not a big deal. to take care

    // SoundPool is known to have problems with MP3 files and long sound files,
    // where long is defined as “longer than 5 to 6 seconds.” (Both problems are undocumented)
    // so there are no strict rules for deciding whether your sound effect will be troublesome or not.
    // As a general rule we should sticking to OGG audio files instead of MP3s, and trying for
    // the lowest possible sampling rate and duration we can get away with before the audio quality becomes poor.
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        // sync our audio system to android audio system (ie : user clicks to increase/decrease volume sound)
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        // maximum is 20 songs play concurrency
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public AndroidSound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }   
}
