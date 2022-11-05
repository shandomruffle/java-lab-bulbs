public abstract class LightingBulb {
    private String producer;
    private int power;

    protected LightingBulb(String pr, int pw) {
        this.power = pw;
        this.producer = pr;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public abstract String toString();

    public abstract long getCost();
}
