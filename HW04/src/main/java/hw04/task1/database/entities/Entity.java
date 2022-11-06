package hw04.task1.database.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    
    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
