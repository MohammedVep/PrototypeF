package com.georgebrown.prototype1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.georgebrown.prototype1.placeholder.RestaurantContent;

import com.georgebrown.prototype1.Model.Restaurant;


import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RESTAURANT_ID = "0";
    // TODO: Rename and change types of parameters
    private String restaurant_id;
    private Restaurant restaurant;

    public RestaurantViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RestaurantViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantViewFragment newInstance(String position) {
        RestaurantViewFragment fragment = new RestaurantViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESTAURANT_ID,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurant_id = getArguments().getString(ARG_RESTAURANT_ID);
            restaurant = RestaurantContent.ITEMS.get(Integer.valueOf(restaurant_id));
        }
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_restaurant_view, container,
                false);
        if (restaurant != null){
            TextView restaurantName = v.findViewById(R.id.viewRestaurantName);
            TextView address = v.findViewById(R.id.viewRestaurantAddress);
            TextView city = v.findViewById(R.id.viewRestaurantCity);
            TextView state = v.findViewById(R.id.viewRestaurantState);
            TextView zipcode = v.findViewById(R.id.viewRestaurantZip);
            TextView tasks = v.findViewById(R.id.viewRestaurantTasks);
            TextView tags = v.findViewById(R.id.viewRestaurantTags);
            TextView phone = v.findViewById(R.id.viewRestaurantPhone);
            TextView email = v.findViewById(R.id.viewRestaurantEmail);
            restaurantName.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
            city.setText(restaurant.getCity());
            state.setText(restaurant.getState());
            zipcode.setText(restaurant.getZip());
            tasks.setText(restaurant.getTasks());
            tags.setText(restaurant.getTasks());
            phone.setText(restaurant.getPhone());
            email.setText(restaurant.getEmail());
            String fullAddress = restaurant.getAddress()+" " + restaurant.getCity() +" " +restaurant.getState() +" "+restaurant.getZip();

            getFragmentManager().beginTransaction().replace(R.id.vewRestaurantMap,MapFragment.newInstance(fullAddress)).commit();



            Button delete = v.findViewById(R.id.restaurantViewDelete);
            delete.setOnClickListener(view -> {
                RestaurantContent.ITEMS.remove(Integer.valueOf(restaurant_id)+0);
                getFragmentManager().popBackStack();
            });

            Button shareBtn = v.findViewById(R.id.shareBtn);
            shareBtn.setOnClickListener(view -> {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "PrototypeF");
                String shareMessage= "\nCheck this restaurant out\n\n";
                shareMessage = shareMessage + "http://maps.google.ca/maps?q=" + encodeValue(fullAddress);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            });

        }
        return v;
    }
}