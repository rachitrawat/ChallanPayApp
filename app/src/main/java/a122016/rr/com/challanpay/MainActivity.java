package a122016.rr.com.challanpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mBookNumber;
    private EditText mChallanNumber;
    private EditText mPhoneNumber;
    private ProgressBar mProgressBar;
    private Button mCheckDetails;
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
        mNameText = (TextView) findViewById(R.id.name_text_view);
        mVehiclenumberText = (TextView) findViewById(R.id.vehicleNo_text_view);
        mChallanDateText = (TextView) findViewById(R.id.challan_date_text_view);
        mAmountText = (TextView) findViewById(R.id.amount_text_view);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNameText.setVisibility(View.INVISIBLE);
        mVehiclenumberText.setVisibility(View.INVISIBLE);
        mChallanDateText.setVisibility(View.INVISIBLE);
        mAmountText.setVisibility(View.INVISIBLE);

    }
}
