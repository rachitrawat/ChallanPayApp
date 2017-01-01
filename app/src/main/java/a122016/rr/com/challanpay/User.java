package a122016.rr.com.challanpay;

/**
 * Created by rachitrawat on 12/26/2016.
 */

public class User {

    private String mName;
    private String mVehicleNumber;
    private String mChallanDate;
    private String mArea;
    private String mDLNumber;
    private String mVehicleType;
    private String mCollectedDocuments;
    private int mAmount;
    private boolean mAmountStatus;

    public User(String mName, String mVehicleNumber, String mChallanDate, String mArea, String mDLNumber, String mVehicleType, String mCollectedDocuments, int mAmount, boolean mAmountStatus) {
        this.mName = mName;
        this.mVehicleNumber = mVehicleNumber;
        this.mChallanDate = mChallanDate;
        this.mArea = mArea;
        this.mDLNumber = mDLNumber;
        this.mVehicleType = mVehicleType;
        this.mCollectedDocuments = mCollectedDocuments;
        this.mAmount = mAmount;
        this.mAmountStatus = mAmountStatus;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmVehicleNumber() {
        return mVehicleNumber;
    }

    public void setmVehicleNumber(String mVehicleNumber) {
        this.mVehicleNumber = mVehicleNumber;
    }

    public String getmChallanDate() {
        return mChallanDate;
    }

    public void setmChallanDate(String mChallanDate) {
        this.mChallanDate = mChallanDate;
    }

    public int getmAmount() {
        return mAmount;
    }

    public void setmAmount(int mAmount) {
        this.mAmount = mAmount;
    }

    public String getmArea() {
        return mArea;
    }

    public void setmArea(String mArea) {
        this.mArea = mArea;
    }

    public String getmDLNumber() {
        return mDLNumber;
    }

    public void setmDLNumber(String mDLNumber) {
        this.mDLNumber = mDLNumber;
    }

    public String getmVehicleType() {
        return mVehicleType;
    }

    public void setmVehicleType(String mVehicleType) {
        this.mVehicleType = mVehicleType;
    }

    public boolean ismAmountStatus() {
        return mAmountStatus;
    }

    public void setmAmountStatus(boolean mAmountStatus) {
        this.mAmountStatus = mAmountStatus;
    }


    public String getmCollectedDocuments() {
        return mCollectedDocuments;
    }

    public void setmCollectedDocuments(String mCollectedDocuments) {
        this.mCollectedDocuments = mCollectedDocuments;
    }
}
