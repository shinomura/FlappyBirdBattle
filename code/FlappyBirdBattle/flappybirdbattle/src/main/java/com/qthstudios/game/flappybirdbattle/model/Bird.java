package com.qthstudios.game.flappybirdbattle.model;

import com.qthstudios.game.flappybirdbattle.config.FapAssets;
import com.qthstudios.game.flappybirdbattle.framework.gl.Animation;
import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;
import com.qthstudios.game.flappybirdbattle.framework.gl.TextureRegion;
import com.qthstudios.game.flappybirdbattle.framework.math.Vector2;
import com.qthstudios.game.flappybirdbattle.framework.signature.DynamicGameObject;
import com.qthstudios.game.flappybirdbattle.model.custom_model.World;

/**
 * Created by ThaoHQSE60963 on 2/11/14.
 */
public class Bird extends DynamicGameObject {

    private float MAX_ANGLE_UP = 25;

    public Bird(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public static final Vector2 gravity = new Vector2(0, -1000);
    public static final float BIRD_WIDTH = 48;
    public static final float BIRD_HEIGHT = 48;

    public SpriteBatcher batcher;

    String color; // Example: FapAssets.AnimateAsset.yellow_bird
    float stateTime;

    private TextureRegion _keyframe;

    public Bird(SpriteBatcher b, String c, float x, float y) {
        super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
        batcher = b;
        color = c;
        stateTime = 0;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;

        velocity.add(gravity.x * deltaTime, gravity.y * deltaTime);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

        float angle = velocity.y / 5;
        if (angle > MAX_ANGLE_UP) {
            angle = MAX_ANGLE_UP;
        }
        if (angle < -90) {
            angle = -90;
        }

        _keyframe = FapAssets.animations.get(color).getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);
        batcher.drawSprite(position.x, position.y, BIRD_WIDTH, BIRD_HEIGHT, angle, _keyframe);
    }

    public void hitPipe() {
        throw new UnsupportedOperationException();
    }

    public void hitTop() {
        throw new UnsupportedOperationException();
    }
    public void hitBot() {
        throw new UnsupportedOperationException();
    }
//
//    public void hitSquirrel() {
//        velocity.set(0,0);
//        state = BOB_STATE_HIT;
//        stateTime = 0;
//    }
//
//    public void hitPlatform() {
//        velocity.y = BOB_JUMP_VELOCITY;
//        state = BOB_STATE_FLY;
//        stateTime = 0;
//    }
//
//    public void hitSpring() {
//        velocity.y = BOB_JUMP_VELOCITY * 1.5f;
//        state = BOB_STATE_FLY;
//        stateTime = 0;
//    }
}
