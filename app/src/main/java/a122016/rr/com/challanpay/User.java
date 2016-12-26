package a122016.rr.com.challanpay;

/**
 * Created by rachitrawat on 12/26/2016.
 */

public class User {

    private String mName;
    private String mVehicleNumber;
    private String mChallanDate;
    private int mAmount;
    private boolean mAmountIsPaid;

    public User(String mName, String mVehicleNumber, String mChallanDate, int mAmount, boolean mAmountIsPaid) {
        this.mName = mName;
        this.mVehicleNumber = mVehicleNumber;
        this.mChallanDate = mChallanDate;
        this.mAmount = mAmount;
        this.mAmountIsPaid = mAmountIsPaid;
    }

    public String getmName() {
        return mName;
    }

    public String getmVehicleNumber() {
        return mVehicleNumber;
    }

    public String getmChallanDate() {
        return mChallanDate;
    }

    public int getmAmount() {
        return mAmount;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmVehicleNumber(String mVehicleNumber) {
        this.mVehicleNumber = mVehicleNumber;
    }

    public void setmChallanDate(String mChallanDate) {
        this.mChallanDate = mChallanDate;
    }

    public boolean ismAmountIsPaid() {
        return mAmountIsPaid;
    }

    public void setmAmountIsPaid(boolean mAmountIsPaid) {
        this.mAmountIsPaid = mAmountIsPaid;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;

    }
}
