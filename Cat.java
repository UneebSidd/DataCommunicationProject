import java.io.Serializable;

public class Cat implements Serializable {
    private String name;
    private int x;
    private int y;
    private String color;
    private String foodPreference;
    private boolean isFed;

    public Cat(String cName, int cX, int cY, String cColor, String cFoodPreference) {
        this.name = cName;
        this.x = cX;
        this.y = cY;
        this.color = cColor;
        this.foodPreference = cFoodPreference;
        this.isFed = false;
    }

    public synchronized void setFed(boolean isFed) {
        this.isFed = isFed;
    }

    public synchronized String getName() {
        return this.name;
    }

    public synchronized String getColor() {
        return this.color;
    }

    public synchronized String getFoodPreference() {
        return this.foodPreference;
    }

    public synchronized boolean isFed() {
        return this.isFed;
    }

    public synchronized int getX() {
        return this.x;
    }

    public synchronized int getY() {
        return this.y;
    }

    @Override
    public synchronized String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                ", foodPreference='" + foodPreference + '\'' +
                ", isFed=" + isFed +
                '}';
    }
}
