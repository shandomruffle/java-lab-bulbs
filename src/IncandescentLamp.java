public class IncandescentLamp extends LightingBulb {
    private int exploitation;
    static final double CONST1 = 5;

    public IncandescentLamp(String pr, int pw, int exp) {
        super(pr, pw);
        this.exploitation = exp;
    }

    @Override
    public String toString() {
        return "prod: " + getProducer() +
                "cost: " + getCost() +
                "exp: " + getExploitation();
    }

    @Override
    public long getCost() {
        return Math.round(getPower() * CONST1 * exploitation);
    }

    public int getExploitation() {
        return exploitation;
    }

    public void setExploitation(int exploitation) {
        this.exploitation = exploitation;
    }
}
