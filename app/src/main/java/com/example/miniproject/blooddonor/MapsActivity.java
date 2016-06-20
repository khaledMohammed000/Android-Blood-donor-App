package com.example.miniproject.blooddonor;

/**
 * Created by AzMak Pirates on 11/3/2015.
 */

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 14.0f;//This goes up to 21
        // Add a marker in here and move the camera
        LatLng MJCET = new LatLng(17.4278, 78.4425);
        LatLng Bank1 = new LatLng(17.441382,78.501213);
        LatLng Bank2 = new LatLng(17.393595,78.4849809);
        LatLng Bank3 = new LatLng(17.411868,78.449770);
        LatLng Bank4 = new LatLng(17.470768,78.311407);
        LatLng Bank5 = new LatLng(17.403332,78.479706);
        LatLng Bank6 = new LatLng(17.397019,78.490123);
        LatLng Bank7 = new LatLng(17.437474,78.481911);
        LatLng Bank8 = new LatLng(17.421283,78.449213);
        LatLng Bank9 = new LatLng(17.376515,78.494922);
        LatLng Bank0 = new LatLng(17.480141,78.557309);


        mMap.addMarker(new MarkerOptions().position(Bank1).title("Janavi Voluntary Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(MJCET).title("MJCET"));
        mMap.addMarker(new MarkerOptions().position(Bank2).title("Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank3).title("Aarohi Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank4).title("Kanchi Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank5).title("Lions CLub of Hyderabad"));
        mMap.addMarker(new MarkerOptions().position(Bank6).title("Ratna & Co"));
        mMap.addMarker(new MarkerOptions().position(Bank7).title("Cauvery Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank8).title("ITMR Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank9).title("Yashoda Blood Bank"));
        mMap.addMarker(new MarkerOptions().position(Bank0).title("Asian Blood Bank"));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MJCET, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank1, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank2, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank3, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank4, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank5, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank6, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank7, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank8, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank9, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bank0, zoomLevel));
    }
}
