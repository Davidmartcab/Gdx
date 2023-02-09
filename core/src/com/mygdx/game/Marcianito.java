package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

public class Marcianito {

    HashMap<Integer, Texture> texturas = new HashMap<Integer, Texture>(){{
        put(1, new Texture("Marcianito1.png"));
        put(2, new Texture("Marcianito2.png"));
        put(3, new Texture("Marcianito3.png"));
    }};

    private static int AVANZAR = 5;
    private static final int AVANZARZ = 5;
    private final SpriteBatch batch;
    private Texture marcianito;
    private final Vector3 position;
    private int state = 0;
    private final Score score;
    private final int lvl;
    private int direction;
    private final int widthMarciano;
    private final int heightMarciano;
    private final int lessBottom;
    private final int topWidth;
    private final int topHeight;

    public Marcianito(Score score, int lvl, int topHeight, int topWidth){
        this.topHeight = topHeight;
        this.topWidth = topWidth;
        widthMarciano = (int)(topWidth * 0.13);
        heightMarciano = (int)(topHeight * 0.0633334);
        lessBottom = (int)(topHeight * 0.05);
        position = new Vector3(0, topHeight, 0);
        randomMarciano();
        batch = new SpriteBatch();
        this.score = score;
        this.lvl = lvl;
        this.direction = (int)(Math.random()*2);
    }

    public void pintarMarciano(){
        batch.begin();
        batch.draw(marcianito, position.x, position.y, widthMarciano, heightMarciano);
        batch.end();
    }

    public void mover(float delta, int bossY){
        if(state == 0) return;
        if(position.y > lessBottom) position.y -= AVANZAR*delta*30;
        else {
            score.removeScore();
            randomMarciano();
            position.y = bossY;
        }
    }

    public void zigzag(float delta, int bossY, int fps){
        if(state == 0) return;
        if(fps%60 == 0) direction = (int)(Math.random()*2);
        if(position.y > lessBottom) position.y -= AVANZAR*delta*30;
        else {
            score.removeScore();
            randomMarciano();
            position.y = bossY;
        }
        if(direction == 0){
            if(position.x < topWidth-widthMarciano) position.x += AVANZARZ*delta*30;
            else direction = 1;
        }else{
            if(position.x > 0) position.x -= AVANZARZ*delta*30;
            else direction = 0;
        }
    }

    public boolean tocado(Rectangle disparoHitBox){
        return disparoHitBox.overlaps(new Rectangle(position.x, position.y, widthMarciano, heightMarciano));
    }

    public void start(){
        if(state == 0) state = 1;
        else {
            state = 0;
            randomMarciano();
        }
        position.y = topHeight;
    }

    public void start(int bossY){
        if(state == 0) state = 1;
        else {
            state = 0;
            randomMarciano();
        }
        position.y = bossY;
    }

    private void randomMarciano(){
        int numRand = (int)(Math.random()*3)+1;
        marcianito = texturas.get(numRand);

        position.x = (int)(Math.random()*(topWidth-widthMarciano));
        AVANZAR = (int)(Math.random()*5)+(10 + 10*(lvl/10));
    }

    public void liberar(){
        marcianito.dispose();
        batch.dispose();
    }

}
