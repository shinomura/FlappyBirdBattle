package com.qthstudios.game.flappybirdbattle.framework.impl;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.qthstudios.game.flappybirdbattle.framework.signature.*;

public abstract class AndroidGame extends Activity implements Game {

    /** static class to decide how to render scene */
    public static class RenderSetting {
        static boolean isBufferWidth = false;
        static boolean isBufferHeight = false;
        static int frameExpandBufferedWidth = 10;
        static int frameExpandBufferedHeight = 10;
    }

    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /** decide ratio of scene */
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 480 : 320;
        /** decide how to expand buffer base on Render Setting */
        frameBufferWidth =   RenderSetting.isBufferWidth ? frameBufferWidth + RenderSetting.frameExpandBufferedWidth : frameBufferWidth;
        int frameBufferHeight = isLandscape ? 320 : 480;
        /** decide how to expand buffer base on Render Setting */
        frameBufferHeight = RenderSetting.isBufferHeight ? frameBufferHeight + RenderSetting.frameExpandBufferedHeight : frameBufferHeight;
        /** create frame buffer for render image */
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
        setContentView(renderView);
    }

    @Override
    public void onResume() {
        super.onResume();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        renderView.pause();
        screen.pause();

        if (isFinishing()) {
            screen.dispose();
        }
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    /** this method will often call in Game.Update() method of screen instance */
    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        /** we must stop current screen p.224 */
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);

        this.screen = screen;
    }

    public Screen getCurrentScreen() {
        return screen;
    }   
}