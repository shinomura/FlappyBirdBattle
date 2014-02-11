package com.qthstudios.game.flappybirdbattle.screen;

import com.qthstudios.game.flappybirdbattle.config.Assets;
import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.config.Settings;
import com.qthstudios.game.flappybirdbattle.framework.signature.Screen;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLGame;
import com.qthstudios.game.flappybirdbattle.screencast.MainScreen;
import com.qthstudios.game.flappybirdbattle.test.TestScreen;

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
        return new TestScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate) {
            Settings.load(getFileIO());
            Assets.load(this);
            FapAssets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.reload();
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
