package io.eugenzilla.game;

import io.eugenzilla.IO.Input;

import java.awt.*;

public abstract class Entity {

    public final EntityType entityType;

    protected float x;
    protected float y; // координаты местонахождения

    protected Entity(EntityType entityType, float x, float y) {
        this.entityType = entityType;
        this.x = x;
        this.y = y;
    }

    protected abstract void update(Input input);

    protected abstract void render(Graphics2D graphics2D);
}
