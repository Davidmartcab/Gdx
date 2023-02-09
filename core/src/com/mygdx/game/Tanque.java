package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Tanque extends Thread {

    private final int AVANZAR;
    private final SpriteBatch batch;
    private final Texture tanque;
    private final Vector3 position;
    private final int finX;
    private final int inicioX;
    private final int widthTanque;
    private final int heightTanque;
    private final Disparo dis;

    public Tanque(Disparo dis, int topHeight, int topWidth){
        widthTanque = (int)(topWidth * 0.175);
        heightTanque = (int)(topHeight * 0.09166667);
        int lessBottom = (int) (topHeight * 0.0166667);
        inicioX = (int)(topWidth * 0.025);
        finX = (int)(topWidth * 0.8);
        int startX = (int) (topWidth * 0.4125);
        AVANZAR = (int)(topWidth * 0.0125);
        position = new Vector3(startX, lessBottom, 0);
        tanque = new Texture("Tanque1.png");
        batch = new SpriteBatch();
        this.dis = dis;
    }

    public int getX(){
        return (int)position.x;
    }

    public void pintarPersonaje(){
        batch.begin();
        batch.draw(tanque, position.x, position.y, widthTanque, heightTanque);
        batch.end();
    }

    public void moverDerecha(){
        if(position.x < finX) position.x += AVANZAR;
    }

    public void moverIzquierda(){
        if(position.x > inicioX) position.x -= AVANZAR;
    }

    public void liberar(){
        tanque.dispose();
        batch.dispose();
    }

    public boolean tocado(Rectangle disparoHitBox){
        return disparoHitBox.overlaps(new Rectangle(position.x, position.y, widthTanque, heightTanque));
    }

    public void disparar(){
        dis.disparar((int)position.x);
    }


}
