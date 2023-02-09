package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Explosion {
    private final SpriteBatch batch;
    private final Texture explosion;
    private final Vector3 position;
    private int state = 1;
    private int fpsExpo = 0;
    private final int widthExplosion;
    private final int heightExplosion;
    private final int topHeight;

    public Explosion(int topHeight, int topWidth) {
        this.topHeight = topHeight;
        widthExplosion = (int)(topWidth * 0.075);
        heightExplosion = (int)(this.topHeight * 0.05);
        position = new Vector3(-100, topHeight, 0);
        explosion = new Texture("Explosion1.png");
        batch = new SpriteBatch();
    }

    public void pintarExplosion(){
        if(state == 1) fpsExpo ++;
        if(fpsExpo == 20) {
            state = 0;
            position.y = topHeight;
        }
        batch.begin();
        batch.draw(explosion, position.x, position.y, widthExplosion, heightExplosion);
        batch.end();
    }

    public void hit(int x, int y){
        fpsExpo = 0;
        state = 1;
        position.x = x;
        position.y = y;
    }

}
