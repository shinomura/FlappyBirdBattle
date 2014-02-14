package com.qthstudios.game.flappybirdbattle.model;

import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;
import com.qthstudios.game.flappybirdbattle.framework.signature.DynamicGameObject;

/**
 * Created by ThaoHQSE60963 on 2/13/14.
 */
public class PipePair extends DynamicGameObject {

    private int _pipeSpace = 390;
    private int _defaultOffset = 70;

    public SpriteBatcher batcher;
    /**
     * Value from 0 to 230
     */
    public int pipePosition = 0;

    public PipePair(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public PipePair(SpriteBatcher b, float x, float y, float width, float height) {
        super(x, y, width, height);
        batcher = b;
        pipePosition = (int) (Math.random() * 230 + 1);
    }


    public void render(float deltaTime) {
        position.add(velocity.x * deltaTime, 0);
        if (position.x < -bounds.width) {
            position.x = 360;
            pipePosition = (int) (Math.random() * 230 + 1);
        }
        position.y = pipePosition - _defaultOffset;
        batcher.drawSprite(position.x, position.y, bounds.width, bounds.height, FapAssets.textureRegions.get(FapAssets.TextureAsset.pipe_up));
        batcher.drawSprite(position.x, _pipeSpace + position.y, bounds.width, bounds.height, FapAssets.textureRegions.get(FapAssets.TextureAsset.pipe_down));
    }
}
