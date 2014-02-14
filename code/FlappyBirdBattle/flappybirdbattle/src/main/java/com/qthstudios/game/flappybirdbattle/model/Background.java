package com.qthstudios.game.flappybirdbattle.model;

import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;
import com.qthstudios.game.flappybirdbattle.framework.gl.TextureRegion;
import com.qthstudios.game.flappybirdbattle.framework.signature.DynamicGameObject;


/**
 * Created by Dinh Quang Trung on 2/11/14.
 */
public class Background extends DynamicGameObject {
    public TextureRegion texture;
    public float frameDuration;

    SpriteBatcher batcher;

    public Background(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Background(float x, float y, float width, float height, SpriteBatcher b, TextureRegion t, float f) {
        super(x, y, width, height);
        batcher = b;
        texture = t;
        frameDuration = f;
    }

    public void render(float deltaTime) {
        position.add(velocity.x * deltaTime, 0);
        if (position.x < - bounds.width / 2) {
            position.x = bounds.width / 2;
        }

        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

        batcher.drawSprite(position.x, position.y, bounds.width, bounds.height, texture);
        batcher.drawSprite(position.x + bounds.width - 1, position.y, bounds.width, bounds.height, texture);
    }

}
