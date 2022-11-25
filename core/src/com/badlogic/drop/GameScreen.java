package com.badlogic.drop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Drop game;
    Texture TankImage;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    Rectangle Tank;

    public GameScreen(final Drop game) {
        this.game = game;

        // load the images for the droplet and the Tank, 64x64 pixels each
       // dropImage = new Texture(Gdx.files.internal("health.png"));
        TankImage = new Texture(Gdx.files.internal("tank2.png"));
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1229);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the Tank
        Tank = new Rectangle();
        Tank.x = 800 / 2 - 64 / 2; // center the Tank horizontally
        Tank.y = 20; // bottom left corner of the Tank is 20 pixels above
        Tank.width = 100;
        Tank.height = 64;
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 10;
        raindrop.height = 30;
    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the Tank and
        // all drops
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.batch.draw(TankImage, Tank.x, Tank.y, Tank.width, Tank.height);

        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Tank.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            Tank.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            Tank.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the Tank stays within the screen bounds
        if (Tank.x < 0)
            Tank.x = 0;
        if (Tank.x > 800 - 64)
            Tank.x = 800 - 64;

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        TankImage.dispose();
    }
}
