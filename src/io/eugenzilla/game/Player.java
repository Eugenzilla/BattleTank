package io.eugenzilla.game;

import io.eugenzilla.IO.Input;
import io.eugenzilla.graphics.Sprite;
import io.eugenzilla.graphics.SpriteSheet;
import io.eugenzilla.graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    public static final int SPRITE_SIZE = 16;
    public static final int SPRITES_PER_HEADING = 1;

    private Heading heading;
    private Map<Heading, Sprite> spriteMap;
    private float scale;
    private float speed;


    private enum Heading {
        NORTH(0* SPRITE_SIZE, 0* SPRITE_SIZE, 1* SPRITE_SIZE, 1* SPRITE_SIZE),
        EAST(6* SPRITE_SIZE, 0* SPRITE_SIZE, 1* SPRITE_SIZE, 1* SPRITE_SIZE),
        SOUTH(4* SPRITE_SIZE, 0* SPRITE_SIZE, 1* SPRITE_SIZE, 1* SPRITE_SIZE),
        WEST(2* SPRITE_SIZE, 0* SPRITE_SIZE, 1* SPRITE_SIZE, 1* SPRITE_SIZE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;

        }

        protected BufferedImage texture(TextureAtlas textureAtlas) {
            return textureAtlas.cutImage(x,y,w,h);
        }
    }

    protected Player(float x, float y, float scale, float speed, TextureAtlas textureAtlas) {
        super(EntityType.Player, x, y);

        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;

        for (Heading h : Heading.values()) {
            SpriteSheet spriteSheet = new SpriteSheet(h.texture(textureAtlas), SPRITES_PER_HEADING, SPRITE_SIZE);
            Sprite sprite = new Sprite(spriteSheet, scale);
            spriteMap.put(h, sprite);
        }
    }

    @Override
    protected void update(Input input) {
        float newX = x;
        float newY = y;

        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
            heading = Heading.NORTH;
        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.EAST;
        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
            heading = Heading.SOUTH;
        } else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.WEST;
        }

        if (newX < 0) {
            newX = 0;
        } else if(newX >= Game.WIDTH - SPRITE_SIZE * scale -1) {
            newX = Game.WIDTH - SPRITE_SIZE * scale -1;
        } else if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - SPRITE_SIZE * scale -1) {
            newY = Game.HEIGHT - SPRITE_SIZE * scale -1;
        }

        x = newX;
        y = newY;
    }

    @Override
    protected void render(Graphics2D graphics2D) {
        spriteMap.get(heading).render(graphics2D, x, y);
    }
}
