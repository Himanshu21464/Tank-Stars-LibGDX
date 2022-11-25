package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoginScreen implements Screen {
    final Drop game;
    private Texture menuImage;
    private TextureRegion menuTexture;
    OrthographicCamera camera;
    public LoginScreen(final Drop game) {
        this.game=game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        menuImage = new Texture(Gdx.files.internal("menu.png"));
        menuTexture = new TextureRegion(menuImage, 0, 0, 1920, 1229);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1229);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
            ScreenUtils.clear(0, 0, 0, 0);

            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            game.batch.draw(menuTexture, 0,0, 1920, 1229);
            game.batch.end();

            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                game.setScreen(new GameScreen(game));
                dispose();
            } else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                game.setScreen(new GameScreen(game));
                dispose();
            }  else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                System.exit(0);
            }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
