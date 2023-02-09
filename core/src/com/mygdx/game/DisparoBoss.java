package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class DisparoBoss {

    private static int AVANZAR;
    private final SpriteBatch batch;
    private final Texture disparoBoss;
    private final Vector3 position;
    private int state = 1;
    private final Rectangle area;

    private final int widthDisparo;
    private final int heightDisparo;
    private final int plusTanque;

    public DisparoBoss(int lvl, int topHeight, int topWidth) {
        position = new Vector3(-100, topHeight, 0);
        disparoBoss = new Texture("DisparoBoss1.png");
        widthDisparo = (int)(topWidth * 0.04);
        heightDisparo = (int)(topHeight * 0.0933334);
        plusTanque = (int)(topWidth * 0.075);
        batch = new SpriteBatch();
        area = new Rectangle(position.x, position.y, widthDisparo, heightDisparo);
        int velNormal = (int)(topHeight * 0.03333333334);
        AVANZAR = (int)(velNormal + ((velNormal * 1.1)/15)*lvl);
    }

    public int getX() {
        return (int) position.x;
    }

    public void setX(int x) {
        position.x = x;
    }

    public int getY() {
        return (int) position.y;
    }

    public void setState(int state) {
        this.state = state;
    }
    public Rectangle getArea() {
        return area;
    }

    public void pintarDisparoBoss(){
        batch.begin();
        batch.draw(disparoBoss, position.x, position.y, widthDisparo, heightDisparo);
        batch.end();
    }

    public void mover(float delta, int xTanque, int yBoss){
        if(state == 0) return;
        if(position.y > 0) position.y -= AVANZAR*delta*30;
        else {
            Gdx.audio.newSound(Gdx.files.internal("shot2.mp3")).play();
            position.y = yBoss;
            position.x = xTanque+plusTanque;
        }
        area.setPosition(position.x, position.y);
    }

    public void recarga(int posX, int posY){
        state = 1;
        position.y = posY;
        position.x = posX;
        area.setPosition(position.x, position.y);
    }

    public void liberar(){
        disparoBoss.dispose();
        batch.dispose();
    }


}
