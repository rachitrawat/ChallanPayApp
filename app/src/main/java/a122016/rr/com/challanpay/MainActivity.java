package a122016.rr.com.challanpay;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    private static final String LOG_TAG = MainActivity.class.getName();
    /**
     * URL for user data from the server
     */
    private static final String USER_REQUEST_URL =
            "https://api.myjson.com/bins/lt6fz";
    /**
     * Constant value for the user loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int USER_LOADER_ID = 1;
    private EditText mBookNumber;
    private EditText mChallanNumber;
    //  private EditText mDate;
    private ProgressBar mProgressBar;
    private Button mCheckDetails;
    private Button mPayButton;
    private TextView mNameText;
    private TextView mVehiclenumberText;
    private TextView mChallanDateText;
    private TextView mAmountText;
    private TextView mAreaText;
    private TextView mVehicleTypeText;
    private TextView mDLNumberText;
    private TextView mCollectedDocumentsText;

    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookNumber = (EditText) findViewById(R.id.bookNo_text_view);
        mChallanNumber = (EditText) findViewById(R.id.challanNo_text_view);
        //      mDate = (EditText) findViewById(R.id.date_text_view);
        mAreaText = (TextView) findViewById(R.id.area_text_view);
        mVehicleTypeText = (TextView) findViewById(R.id.vehicle_type_text_view);
        mDLNumberText = (TextView) findViewById(R.id.DL_Number_text_view);
        mCollectedDocumentsText = (TextView) findViewById(R.id.Collected_Documents_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mCheckDetails = (Button) findViewById(R.id.check_status_button);
        mPayButton = (Button) findViewById(R.id.pay_button);
        mNameText = (TextView) findViewById(R.id.name_text_view);
        mVehiclenumberText = (TextView) findViewById(R.id.vehicleNo_text_view);
        mChallanDateText = (TextView) findViewById(R.id.challan_date_text_view);
        mAmountText = (TextView) findViewById(R.id.amount_text_view);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNameText.setVisibility(View.INVISIBLE);
        mAreaText.setVisibility(View.INVISIBLE);
        mDLNumberText.setVisibility(View.INVISIBLE);
        mCollectedDocumentsText.setVisibility(View.INVISIBLE);
        mVehicleTypeText.setVisibility(View.INVISIBLE);
        mVehiclenumberText.setVisibility(View.INVISIBLE);
        mChallanDateText.setVisibility(View.INVISIBLE);
        mAmountText.setVisibility(View.INVISIBLE);
        mPayButton.setVisibility(View.INVISIBLE);
    }

    public void execute_it(View view) {

        if (mCheckDetails.getText().toString().equals("Reset")) {
            // restart the activity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        // Check if no view has focus:
        // hide keyboard
        View view1 = this.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }

        //  if (!mBookNumber.getText().toString().matches("") && mBookNumber.getText().toString().length() == 4 && !mChallanNumber.getText().toString().matches("") && !mDate.getText().toString().matches("") && isValidFormat("dd/MM/yyyy", mDate.getText().toString())) {

        if (!mBookNumber.getText().toString().matches("") && mBookNumber.getText().toString().length() == 4 && !mChallanNumber.getText().toString().matches("")) {

            // Get a reference to the ConnectivityManager to check state of network connectivity
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {
                //make progress bar visible
                mProgressBar.setVisibility(View.VISIBLE);

                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(USER_LOADER_ID, null, this);
            } else {
                Toast.makeText(this, "Network Connection Required.", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "Please enter all fields correctly.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public Loader<User> onCreateLoader(int id, Bundle args) {
        return new UserLoader(this, USER_REQUEST_URL);


    }

    @Override
    public void onLoadFinished(Loader<User> loader, User data) {
        mNameText.setText("Name: " + data.getmName());
        mVehiclenumberText.setText("Vehicle Number: " + data.getmVehicleNumber());
        mChallanDateText.setText("Challan Date: " + data.getmChallanDate());
        mVehicleTypeText.setText("Vehicle Type: " + data.getmVehicleType());
        mAreaText.setText("Area: " + data.getmArea());
        mCollectedDocumentsText.setText("Collected Documents: " + data.getmCollectedDocuments());
        mDLNumberText.setText("DL Number: " + data.getmDLNumber());
        if (data.ismAmountStatus()) {
            mAmountText.setText(String.valueOf("Amount paid: " + data.getmAmount()));
            mAmountText.setTextColor(Color.parseColor("#388E3C"));
        } else {
            mAmountText.setText(String.valueOf("Amount due: Rs." + data.getmAmount()));
            mAmountText.setTextColor(Color.RED);
            mPayButton.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
        mNameText.setVisibility(View.VISIBLE);
        mVehiclenumberText.setVisibility(View.VISIBLE);
        mChallanDateText.setVisibility(View.VISIBLE);
        mAmountText.setVisibility(View.VISIBLE);
        mVehicleTypeText.setVisibility(View.VISIBLE);
        mDLNumberText.setVisibility(View.VISIBLE);
        mCollectedDocumentsText.setVisibility(View.VISIBLE);
        mAreaText.setVisibility(View.VISIBLE);
        mCheckDetails.setText("Reset");
        mBookNumber.setVisibility(View.GONE);
        mChallanNumber.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<User> loader) {

    }
}
