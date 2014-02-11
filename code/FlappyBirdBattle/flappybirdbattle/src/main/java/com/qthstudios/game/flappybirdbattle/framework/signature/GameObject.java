package com.qthstudios.game.flappybirdbattle.framework.signature;

import com.qthstudios.game.flappybirdbattle.framework.math.Rectangle;
import com.qthstudios.game.flappybirdbattle.framework.math.Vector2;

/**
 * Entity to describe object screen
 */
public class GameObject {
    /** current position of object */
    public final Vector2 position;
    /** boundary for game object. use to recognize user's touch location */
    public final Rectangle bounds;
    
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
    }
}
