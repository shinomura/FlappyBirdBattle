package com.qthstudios.game.flappybirdbattle.test;

import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.framework.gl.Camera2D;
import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;
import com.qthstudios.game.flappybirdbattle.framework.gl.TextureRegion;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLScreen;
import com.qthstudios.game.flappybirdbattle.framework.math.OverlapTester;
import com.qthstudios.game.flappybirdbattle.framework.math.Rectangle;
import com.qthstudios.game.flappybirdbattle.framework.math.Vector2;
import com.qthstudios.game.flappybirdbattle.framework.signature.Game;
import com.qthstudios.game.flappybirdbattle.framework.signature.Input;
import com.qthstudios.game.flappybirdbattle.model.Background;
import com.qthstudios.game.flappybirdbattle.model.Bird;
import com.qthstudios.game.flappybirdbattle.model.Pipe;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static com.qthstudios.game.flappybirdbattle.config.FapAssets.TextureAsset;
import static com.qthstudios.game.flappybirdbattle.utils.LogUtils.LOGE;

/**
 * Created by Dinh Quang Trung on 2/11/14.
 */
public class TestScreen extends GLScreen {
    Camera2D guiCam;
    private SpriteBatcher batcher;


    Rectangle player1Area;
    Rectangle player2Area;

    private Background background1;
    private Background background2;

    private Vector2 touchPoint;

    Pipe pipe;

    Bird bird1;
    Bird bird2;

    public TestScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 320, 480);
        batcher = new SpriteBatcher(glGraphics, 100);

        background1 = new Background(
                168, 0,
                336, 112,
                batcher,
                null,
                0.2f);
        background1.velocity.x = -110;

        background2 = new Background(
                168, 480,
                336, 112,
                batcher,
                null,
                0.2f);
        background2.velocity.x = -110;

        pipe = new Pipe(10, 10, 50, 50);


        touchPoint = new Vector2();

        player1Area = new Rectangle(0, 0, 320, 240);
        player2Area = new Rectangle(0, 240, 320, 240);

        bird1 = new Bird(batcher, FapAssets.AnimateAsset.red_bird, 120, 170);
        bird1.velocity.set(0, 800);
        bird2 = new Bird(batcher, FapAssets.AnimateAsset.blue_bird, 120, 330 - Bird.BIRD_HEIGHT / 2);
        bird2.velocity.set(0, 800);
    }

    @Override
    public void update(float deltaTime) {
        updateFlying();
    }

    private void updateFlying() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type != Input.TouchEvent.TOUCH_DOWN)
                continue;

            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

            if(OverlapTester.pointInRectangle(player1Area, touchPoint)) {
                LOGE("TRUNGDQ", "touched 1!");
                bird1.velocity.y = 400;
                return;
            }

            if(OverlapTester.pointInRectangle(player2Area, touchPoint)) {
                LOGE("TRUNGDQ", "touched 2!");
                bird2.velocity.y = 150;
                return;
            }

            return;
        }
    }

    private void renderTestObject(float deltaTime) {
        bird1.update(deltaTime);
        bird2.update(deltaTime);
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(FapAssets.atlas);
        batcher.drawSprite(160, 240, 320, 480, FapAssets.textureRegions.get(TextureAsset.bg_day));
        if (background1.texture == null) {
            background1.texture = FapAssets.textureRegions.get(TextureAsset.land);
        }
        background1.render(deltaTime);
        if (background2.texture == null) {
            background2.texture = FapAssets.textureRegions.get(TextureAsset.land);
        }
        background2.render(deltaTime);

        // batcher.drawSprite(168, 300, 100, 100, FapAssets.textureRegions.get(TextureAsset.pipe_up));

        // batcher.drawSprite(30, 70, 100, 600, FapAssets.textureRegions.get(TextureAsset.pipe_up));

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
