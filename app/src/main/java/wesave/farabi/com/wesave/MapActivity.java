package wesave.farabi.com.wesave;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import wesave.farabi.com.wesave.Utility.UtilityLocation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleApiClient googleApiClient;

// maps

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

         Context mContext;
        mContext=MapActivity.this;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // only for gingerbread and newer versions
            boolean resultLocation = UtilityLocation.checkPermission(mContext);
            if (resultLocation) {
                //Allow Permission and Call your Location Activity.
                googleMap.setMyLocationEnabled(true);
            }
        } else {
            //Call your Location Activity
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }



    }

