import java.io.Serializable;
import java.util.Objects;

public class Produce extends PaleoFood implements Serializable {
    private boolean mOrganic;

    public Produce(String name, int calories, int carbs, boolean organic) {
        super(name, calories, carbs);
        mOrganic = organic;
    }

    public boolean isOrganic() {
        return mOrganic;
    }

    public void setOrganic(boolean organic) {
        mOrganic = organic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produce produce = (Produce) o;
        return mOrganic == produce.mOrganic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mOrganic);
    }

    @Override
    public String toString() {
        String organicString = null;
        if (mOrganic)
            organicString = "Organic Produce: ";
        else
            organicString = "Produce: ";
        return organicString + ": " +
                mName +
                ", " + mCalories +
                "g calories, " + mCarbs +
                "g carbs";
    }
}
