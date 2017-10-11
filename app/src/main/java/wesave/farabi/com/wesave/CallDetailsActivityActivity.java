package wesave.farabi.com.wesave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wesave.farabi.com.wesave.Data.CallForDonation;

public class CallDetailsActivityActivity extends AppCompatActivity {


    private  TextView name  ;
    private  TextView callmessage ;
    private TextView number ;
    private TextView bloodtype ;
    private TextView date ;
    private TextView location ;
    private ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_details_activity);

        CallForDonation model = (CallForDonation) getIntent().getSerializableExtra("call");
        setupActionBar();
     setView(model) ;


    }



    private void setView(CallForDonation model) {
   img = (ImageView) findViewById(R.id.imageView) ;
        name = (TextView)  findViewById(R.id.callname) ;
        callmessage = (TextView) findViewById(R.id.callmessage) ;
        number = (TextView) findViewById(R.id.phone) ;
        bloodtype = (TextView) findViewById(R.id.bloodtype) ;
        date = (TextView) findViewById(R.id.date) ;
        location = (TextView) findViewById(R.id.location) ;
        Glide.with(this).load(model.getImgUrl()).into(img);

        name.setText("soumaya jandoubi");
        callmessage.setText(" Need urgent Help please !! ");
        number.setText("+21623248482");
        bloodtype.setText(bloodtype.getText()+ " "+ model.getBloodtype());
        date.setText(date.getText()+ model.getDate());
        location.setText(location.getText()+ " " + model.getLocation());
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);


        }
    }


}
