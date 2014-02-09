package com.qthstudios.game.flappybirdbattle.screen;

import com.qthstudios.game.flappybirdbattle.Config.Assets;
import com.qthstudios.game.flappybirdbattle.Config.Settings;
import com.qthstudios.game.flappybirdbattle.framework.Screen;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLGame;

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
            Assets.reload();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Settings.soundEnabled)
            Assets.music.pause();
    }


}
