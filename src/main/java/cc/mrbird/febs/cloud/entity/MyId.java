package cc.mrbird.febs.cloud.entity;

public class MyId {
    private int id;

    public MyId(int id) {
        this.id = id;
    }

    public static MyId create(int id) {
        return new MyId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
