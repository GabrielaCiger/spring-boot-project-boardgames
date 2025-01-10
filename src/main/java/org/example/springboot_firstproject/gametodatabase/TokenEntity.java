package org.example.springboot_firstproject.gametodatabase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class TokenEntity {

    @Id
    private UUID id;

    private String name;

    private @Nullable UUID ownerId;

    private @Nullable Integer x;
    private @Nullable Integer y;

    public TokenEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Nullable
    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(@Nullable UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Integer getX() {
        return x;
    }

    public void setX(@Nullable Integer x) {
        this.x = x;
    }

    @Nullable
    public Integer getY() {
        return y;
    }

    public void setY(@Nullable Integer y) {
        this.y = y;
    }
}
