package com.qthstudios.game.flappybirdbattle.screen;

import com.qthstudios.game.flappybirdbattle.config.Assets;
import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.config.Settings;
import com.qthstudios.game.flappybirdbattle.framework.Screen;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLGame;
import com.qthstudios.game.flappybirdbattle.screencast.MainScreen;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ThaoHQSE60963 on 2/10/14.
 * Where game starts to play
 */
public class FlappyBird extends GLGame {

    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen() {
        return new MainScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate) {
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            FapAssets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Settings.soundEnabled)
            Assets.music.pause();
    }


}
