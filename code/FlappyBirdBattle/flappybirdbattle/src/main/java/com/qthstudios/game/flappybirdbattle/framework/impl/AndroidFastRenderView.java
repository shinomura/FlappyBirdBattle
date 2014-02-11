package com.qthstudios.game.flappybirdbattle.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Inherited class from SurfaceView that do dirty work on different thread
 * keep an game instance to call game.Update() and game.Present() for TimerTick
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;

    // this flag should be put by volatile keyword.
    // The reason is delicate: the compiler might decide to reorder the statements in the FastRenderView.pause()
    // if it recognizes that there are no dependencies between the first line in that method and the while block.
    // It is allowed to do this if it thinks it will make the code execute faster.
    // However, we depend on the order of execution that we specified in that method.
    // Imagine if the running flag were set after we tried to join the thread.
    // We’d go into an endless loop, as the thread would never terminate.
    volatile boolean running = false;
    
    public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
    }

    /** Notes : we create a new Thread each time this method is called.
     *  so. make a flag to mark does the thread is marked as die ? */
    public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();         
    }

    /**
     * method to re-drawn all business change. and should put in new thread to optimize UI thread
     */
    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();

        // Does surface is destroyed between the calls to SurfaceHolder.getSurface().isValid() and SurfaceHolder.lock()?
        // In fact — this can never happen
        // 1. We know that the Surface is created asynchronously.
        // It is likely that our rendering thread will execute before the Surface is valid.
        // We safeguard against this by not locking the Surface unless it is valid. So that covers the surface creation case.
        // 2. The reason the rendering thread code does not explode from the Surface being destroyed between the validity
        // check and the locking has to with the point in time the Surface gets destroyed.
        // The Surface is always destroyed after we return from the activity’s onPause() method.
        // And since we wait for the thread to die in that method via the call to FastRenderView.pause(),
        // the rendering thread will not be alive anymore when the Surface is actually destroyed.
        // Notes about this confusing
        while(running) {
            if(!holder.getSurface().isValid())
                continue;           
            
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            // update game logic
            game.getCurrentScreen().update(deltaTime);
            // render things to artificial framebuffer
            game.getCurrentScreen().present(deltaTime);

            // decide how scale should the Bitmap canvas is
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);                           
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {                        
        running = false;
        // using try-catch and Thread.join() to be sure, the thread actually dies
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }
        }
    }        
}