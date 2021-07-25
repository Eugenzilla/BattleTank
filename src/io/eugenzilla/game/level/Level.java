package io.eugenzilla.game.level;

import io.eugenzilla.game.Game;
import io.eugenzilla.graphics.TextureAtlas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Level {

    public static final int TILE_SIZE = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int TILE_SCALED_SIZE = TILE_SIZE*TILE_IN_GAME_SCALE;
    public static final int TILES_IN_WIDTH = Game.WIDTH / TILE_SCALED_SIZE;
    public static final int TILES_IN_HEIGHT = Game.HEIGHT / TILE_SCALED_SIZE;

    private int[][] tileMap;
    private Map<TileType, Tile> tiles;

    public Level(TextureAtlas textureAtlas) {
        tileMap = new int[TILES_IN_WIDTH][TILES_IN_HEIGHT];
        tiles = new HashMap<TileType, Tile>();
        tiles.put(TileType.BRICK, new Tile(textureAtlas.cutImage(32 * TILE_SIZE, 0 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(textureAtlas.cutImage(32 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new Tile(textureAtlas.cutImage(32 * TILE_SIZE, 4 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(textureAtlas.cutImage(34 * TILE_SIZE, 4 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE, new Tile(textureAtlas.cutImage(36 * TILE_SIZE, 4 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(textureAtlas.cutImage(36 * TILE_SIZE, 6 * TILE_SIZE, TILE_SIZE, TILE_SIZE), TILE_IN_GAME_SCALE, TileType.EMPTY));

        tileMap[10][10] = TileType.BRICK.numeric();
        tileMap[11][11] = TileType.WATER.numeric();
        tileMap[12][12] = TileType.METAL.numeric();
        tileMap[13][13] = TileType.GRASS.numeric();
        tileMap[14][14] = TileType.ICE.numeric();
    }

    public void update() {

    }

    public void render(Graphics2D graphics2D) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                tiles.get(TileType.fromNumeric(tileMap[i][j])).render(graphics2D, j*TILE_SCALED_SIZE, i*TILE_SCALED_SIZE);
            }
        }
    }
}
