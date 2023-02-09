package com.mygdx.game;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MyGdxGame.WIDTH = width;
		MyGdxGame.HEIGHT = height;
		initialize(new MyGdxGame(height, width), config);
	}
}
