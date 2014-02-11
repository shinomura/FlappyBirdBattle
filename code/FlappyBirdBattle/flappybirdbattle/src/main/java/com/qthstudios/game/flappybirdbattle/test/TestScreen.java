package com.qthstudios.game.flappybirdbattle.test;

import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.framework.Game;
import com.qthstudios.game.flappybirdbattle.framework.gl.Animation;
import com.qthstudios.game.flappybirdbattle.framework.gl.Camera2D;
import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;
import com.qthstudios.game.flappybirdbattle.framework.gl.TextureRegion;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLScreen;

import javax.microedition.khronos.opengles.GL10;

import static com.qthstudios.game.flappybirdbattle.utils.LogUtils.LOGE;

/**
 * Created by Dinh Quang Trung on 2/11/14.
 */
public class TestScreen extends GLScreen {
    Camera2D guiCam;
    private SpriteBatcher batcher;

    private TextureRegion _keyframe;
    private float _statetime = 0;

    public TestScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 320, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
    }

    @Override
    public void update(float deltaTime) {
//        GL10 gl = glGraphics.getGL();
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//        guiCam.setViewportAndMatrices();
//        gl.glEnable(GL10.GL_TEXTURE_2D);
//
//        renderTestObject(deltaTime);
//
//        gl.glDisable(GL10.GL_BLEND);
    }

    private void renderTestObject(float deltaTime) {
        _statetime += deltaTime;

        _keyframe = FapAssets.animations.get(FapAssets.AnimateAsset.yellow_bird).getKeyFrame(_statetime, Animation.ANIMATION_LOOPING);
        batcher.drawSprite(120, 120, 48, 48, _keyframe);

        _keyframe = FapAssets.animations.get(FapAssets.AnimateAsset.blue_bird).getKeyFrame(_statetime, Animation.ANIMATION_LOOPING);
        batcher.drawSprite(220, 120, 64, 64, _keyframe);

        _keyframe = FapAssets.animations.get(FapAssets.AnimateAsset.red_bird).getKeyFrame(_statetime, Animation.ANIMATION_LOOPING);
        batcher.drawSprite(120, 320, 80, 80, _keyframe);
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(FapAssets.atlas);
        batcher.drawSprite(160, 240, 320, 480, FapAssets.textureRegions.get(FapAssets.TextureAsset.bg_day));
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        renderTestObject(deltaTime);

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
