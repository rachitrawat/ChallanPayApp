package a122016.rr.com.challanpay;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    private static final String LOG_TAG = MainActivity.class.getName();
    /**
     * URL for user data from the server
     */
    private static final String USER_REQUEST_URL =
            "https://api.myjson.com/bins/nuhy7";
    /**
     * Constant value for the user loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int USER_LOADER_ID = 1;
    private EditText mBookNumber;
    private EditText mChallanNumber;
    private EditText mPhoneNumber;
    private ProgressBar mProgressBar;
    private Button mCheckDetails;
    private Button mPayButton;
    private TextView mNameText;
    private TextView mVehiclenumberText;
    private TextView mChallanDateText;
    private TextView mAmountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookNumber = (EditText) findViewById(R.id.bookNo_text_view);
        mChallanNumber = (EditText) findViewById(R.id.challanNo_text_view);
        mPhoneNumber = (EditText) findViewById(R.id.phoneNo_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mCheckDetails = (Button) findViewById(R.id.check_status_button);
        mPayButton = (Button) findViewById(R.id.pay_button);
        mNameText = (TextView) findViewById(R.id.name_text_view);
        mVehiclenumberText = (TextView) findViewById(R.id.vehicleNo_text_view);
        mChallanDateText = (TextView) findViewById(R.id.challan_date_text_view);
        mAmountText = (TextView) findViewById(R.id.amount_text_view);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNameText.setVisibility(View.INVISIBLE);
        mVehiclenumberText.setVisibility(View.INVISIBLE);
        mChallanDateText.setVisibility(View.INVISIBLE);
        mAmountText.setVisibility(View.INVISIBLE);
        mPayButton.setVisibility(View.INVISIBLE);
    }

    public void execute_it(View view) {

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
            ;
        }

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
        if (data.getmStatus()) {
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


    }

    @Override
    public void onLoaderReset(Loader<User> loader) {

    }
}
