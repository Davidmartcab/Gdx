package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Boss {
    private static int AVANZAR = 3;
    private final SpriteBatch batch;
    private final Texture boss;
    private final Vector3 position;
    private final Score score;
    private int vida;
    private int maxVida = 20;

    private final int widthBoss;
    private final int heightBoss;
    private final int lessBottom;

    private final int lessTop;

    public Boss(Score score, int vel, int topHeight, int topWidth){
        widthBoss = (int)(Gdx.graphics.getWidth() * 0.963);
        heightBoss = (int)(Gdx.graphics.getHeight() * 0.2334);
        lessBottom = (int)(Gdx.graphics.getHeight() * 0.05);
        lessTop = (int)(topHeight * 0.76667);
        int leftStart = (int) (topWidth * 0.011667);
        position = new Vector3(leftStart, topHeight, 0);
        batch = new SpriteBatch();
        boss = new Texture("Boss1.png");
        this.score = score;
        AVANZAR = (int)(topHeight*0.0083333333334);
        AVANZAR = AVANZAR +  (AVANZAR * (vel/10));
        maxVida = (int)(maxVida * (1.25*(vel)));
        vida = maxVida;
    }

    public int getY(){
        return (int)position.y;
    }

    public int getVida(){
        return vida;
    }

    public int getMaxVida(){
        return maxVida;
    }
    public void pintarBoss(){
        batch.begin();
        batch.draw(boss, position.x, position.y, widthBoss, heightBoss);
        batch.end();
    }

    public boolean tocado(Rectangle disparoHitBox){
        return disparoHitBox.overlaps(new Rectangle(position.x, position.y, widthBoss, heightBoss));
    }

    public void restVida(){
        vida--;
    }
    public void mover(float delta){
        if(position.y > lessBottom) position.y -= AVANZAR*delta*30;
        else {
            Gdx.audio.newSound(Gdx.files.internal("epic.mp3")).play();
            score.removeAllScore();
        }
    }

    public void subir(){
        if(position.y < lessTop) position.y += AVANZAR*4;
    }


    public void liberar(){
        boss.dispose();
        batch.dispose();
    }

}
