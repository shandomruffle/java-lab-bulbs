public class LightEmittingDiodeLamp extends LightingBulb {
    private int leds_count;
    static final double CONST2 = 5;

    public LightEmittingDiodeLamp(String pr, int pw, int cnt) {
        super(pr, pw);
        this.leds_count = cnt;
    }

    @Override
    public long getCost() {
        return Math.round(getPower() * leds_count / CONST2);
    }

    @Override
    public String toString() {
        return "prod: " + getProducer() +
                " cost: " + getCost() +
                " leds: " + getLedsCount(); // add space
    }

    public int getLedsCount() {
        return leds_count;
    }

    public void setLedsCount(int leds_count) {
        this.leds_count = leds_count;
    }
}
