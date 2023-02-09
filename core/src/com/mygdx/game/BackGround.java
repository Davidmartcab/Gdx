package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
public class BackGround {
    SpriteBatch batch;
    private final ArrayList<Texture> map = new ArrayList<Texture>(){{
        add(new Texture("bc1.png"));
        add(new Texture("bc2.png"));
        add(new Texture("bc3.png"));
        add(new Texture("bc4.png"));
    }};

    public BackGround(SpriteBatch batch) {
        this.batch = batch;
    }

    public void pintar(int width, int height, int lvl) {
        batch.begin();
        batch.draw(map.get((lvl-1)%4), 0, 0, width,height);
        batch.end();
    }

    public void liberar(){
        batch.dispose();
    }
}
