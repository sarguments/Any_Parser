package anyparser;

public class Car {

    private String color;
    private String type;

    // 기본 생성자 필수
    public Car() {
    }

    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    // standard getters setters

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
