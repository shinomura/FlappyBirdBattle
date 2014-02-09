package com.qthstudios.game.flappybirdbattle.framework.impl;


import com.qthstudios.game.flappybirdbattle.framework.Game;
import com.qthstudios.game.flappybirdbattle.framework.Screen;

public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;
    
    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = ((GLGame)game).getGLGraphics();
    }

}
