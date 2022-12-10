package com.georgebrown.prototype1;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapFragment extends Fragment {
    private static final String ADDRESS = "";

    private LatLng latLng;

    List<Address> addressList;
    public MapFragment(){}

    public static MapFragment newInstance(String address) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ADDRESS,address);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Geocoder geocoder = new Geocoder(getContext());
            try {
                addressList = geocoder.getFromLocationName(getArguments().getString(ADDRESS),1);

            } catch (IOException e) {
                System.out.println("Wrong address");
            }
            try{
                latLng = new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude());
            } catch (Exception e){
                latLng = new LatLng(43.642567,-79.387054);
                System.out.println("something went wrong");
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Title");
                googleMap.clear();
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng lat) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:"+String.valueOf(latLng.latitude)+","+String.valueOf(latLng.longitude)+"q=" + Uri.encode("restaurants")));
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}