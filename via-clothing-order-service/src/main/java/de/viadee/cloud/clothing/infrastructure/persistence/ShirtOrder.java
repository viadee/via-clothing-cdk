package de.viadee.cloud.clothing.infrastructure.persistence;

import java.time.Instant;
import java.util.Objects;

public final class ShirtOrder {

    String id;

    String orderTimestamp;
    final String type;
    final String size;
    final String color;
    final String logoPosition;
    final String logoColor;
    final String collection;
    final String name;
    final String slogan;


    public ShirtOrder(String type, String size, String color, String collection,
                      String logoPosition, String logoColor,
                      String name, String slogan) {
        this.type = type;
        this.size = size;
        this.color = color;
        this.logoPosition = logoPosition;
        this.logoColor = logoColor;
        this.collection = collection;
        this.name = name;
        this.slogan = slogan;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getLogoPosition() {
        return logoPosition;
    }

    public String getLogoColor() {
        return logoColor;
    }

    public String getCollection() {
        return collection;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(String orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShirtOrder) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.size, that.size) &&
                Objects.equals(this.color, that.color) &&
                Objects.equals(this.logoPosition, that.logoPosition) &&
                Objects.equals(this.logoColor, that.logoColor) &&
                Objects.equals(this.collection, that.collection) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.slogan, that.slogan) &&
                Objects.equals(this.orderTimestamp, that.orderTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size, color, logoPosition, logoColor, collection, name, slogan, orderTimestamp);
    }

    @Override
    public String toString() {
        return "ShirtOrder[" +
                "type=" + type + ", " +
                "size=" + size + ", " +
                "color=" + color + ", " +
                "logoPosition=" + logoPosition + ", " +
                "logoColor=" + logoColor + ", " +
                "collection=" + collection + ", " +
                "name=" + name + ", " +
                "slogan=" + slogan + ", " +
                "orderTimestamp=" + orderTimestamp + ']';
    }

}
