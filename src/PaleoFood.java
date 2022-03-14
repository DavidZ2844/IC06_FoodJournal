import java.util.Objects;

public abstract class PaleoFood {
    protected String mName;
    protected int mCalories, mCarbs;

    protected PaleoFood(String name, int calories, int carbs) {
        mName = name;
        mCalories = calories;
        mCarbs = carbs;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getCalories() {
        return mCalories;
    }

    public void setCalories(int calories) {
        mCalories = calories;
    }

    public int getCarbs() {
        return mCarbs;
    }

    public void setCarbs(int carbs) {
        mCarbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaleoFood paleoFood = (PaleoFood) o;
        return mCalories == paleoFood.mCalories && mCarbs == paleoFood.mCarbs && Objects.equals(mName, paleoFood.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mCalories, mCarbs);
    }
}
