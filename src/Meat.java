import java.io.Serializable;
import java.util.Objects;

public class Meat extends PaleoFood implements Serializable {
    private int mType;
    private int mCookingTemp;

    public Meat(String name, int calories, int type, int cookingTemp) {
        super(name, calories, 0);
        mType = type;
        mCookingTemp = cookingTemp;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getCookingTemp() {
        return mCookingTemp;
    }

    public void setCookingTemp(int cookingTemp) {
        mCookingTemp = cookingTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meat meat = (Meat) o;
        return mType == meat.mType && mCookingTemp == meat.mCookingTemp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mType, mCookingTemp);
    }

    @Override
    public String toString() {
        String typeString = null;
        if (mType == 1)
            typeString = "Meat";
        else if (mType == 2)
            typeString = "Seafood";
        return typeString + ": " +
                mName +
                ", " + mCalories +
                " calories, " + mCarbs +
                "g carbs, " + mCookingTemp + " degrees F";
    }
}
