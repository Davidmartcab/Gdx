package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Disparo {

    private final int AVANZAR;
    private final SpriteBatch batch;
    private final Texture disparo;
    private final Vector3 position;
    public final int finY;
    private int state = 0;
    private final Rectangle area;

    private final int widthDisparo;
    private final int heightDisparo;
    private final int bottomStart;
    private final int plusTanque;

    public Disparo(int topHeight, int topWidth){
        position = new Vector3(-100, topHeight, 0);
        finY = (int)(topHeight * 1.166667);
        AVANZAR = (int)(topHeight *0.083333333334);
        widthDisparo = (int)(topWidth * 0.02);
        heightDisparo = (int)(topHeight * 0.04);
        bottomStart = (int)(topHeight * 0.09166667);
        plusTanque = (int)(topWidth * 0.075);
        disparo = new Texture("Disparo1.png");
        batch = new SpriteBatch();
        area = new Rectangle(position.x, position.y, 8, 24);
    }

    public Rectangle getArea() {
        return area;
    }

    public int getX() {
        return (int) position.x;
    }
    public int getY() {
        return (int) position.y;
    }
    public void pintarDisparo(){
        batch.begin();
        batch.draw(disparo, position.x, position.y, widthDisparo, heightDisparo);
        batch.end();
    }

    public void mover(float delta){
        if(position.y < finY) position.y += AVANZAR*delta*30;
        else {
            state = 1;
            position.x = -100;
        }
        area.setPosition(position.x, position.y);
    }

    public void disparar(int posX){
        if(state == 1){
            Gdx.audio.newSound(Gdx.files.internal("shot1.mp3")).play();
            position.y = bottomStart;
            position.x = posX+plusTanque;
            state = 0;
        }
    }

    public void recarga(){
        state = 1;
        position.x = -100;
    }

    public void liberar(){
        disparo.dispose();
        batch.dispose();
    }

}
