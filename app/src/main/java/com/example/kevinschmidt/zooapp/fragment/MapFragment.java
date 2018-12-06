package com.example.kevinschmidt.zooapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kevinschmidt.zooapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by kevinschmidt on 10.03.16.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

        private GoogleMap googleMap;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_map, container, false);
                return view;
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
                // TODO Auto-generated method stub
                super.onViewCreated(view, savedInstanceState);
                // here, as doc say, the map can be initialized, or the service is not available
                //initilizeMap();
        }
        private void initilizeMap() {
                if (googleMap == null) {
                        ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

                        //Just a text that says that the map is loading = toast
                        if (googleMap == null) {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Loading map ...", Toast.LENGTH_SHORT)
                                        .show();
                        }
                }
        }
        @Override
        public void onMapReady(GoogleMap mGoogleMap) {
                googleMap = mGoogleMap;
        }

}
