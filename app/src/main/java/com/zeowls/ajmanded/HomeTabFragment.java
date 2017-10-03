package com.zeowls.ajmanded;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zeowls.ajmanded.libs.AnimatedFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTabFragment extends AnimatedFragment {


    public HomeTabFragment() {
        // Required empty public constructor
    }

    MapView mMapView;
    private GoogleMap googleMap;
    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    CardView cardView5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_tab, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
//                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        cardView1 = (CardView) view.findViewById(R.id.card1);
        cardView2 = (CardView) view.findViewById(R.id.card2);
        cardView3 = (CardView) view.findViewById(R.id.card3);
        cardView5 = (CardView) view.findViewById(R.id.card5);

        cardView1.setVisibility(View.INVISIBLE);
        cardView2.setVisibility(View.INVISIBLE);
        cardView3.setVisibility(View.INVISIBLE);
        cardView5.setVisibility(View.INVISIBLE);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAnimation();
    }

    @Override
    public void startAnimation(){
        new AnimatedFragment.animate(new View[] { cardView1, cardView2, cardView3, cardView5 }).execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
