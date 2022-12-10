package com.georgebrown.prototype1.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.georgebrown.prototype1.RestaurantAdapter;
import com.georgebrown.prototype1.Model.Restaurant;
import com.georgebrown.prototype1.R;
//import com.georgebrown.prototype1.RestaurantList;
import com.georgebrown.prototype1.RestaurantViewFragment;
import com.georgebrown.prototype1.databinding.FragmentHomeBinding;
import com.georgebrown.prototype1.placeholder.RestaurantContent;
import com.georgebrown.prototype1.ui.addRestaurants.addRestaurants;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RestaurantAdapter.ItemClickListener{


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView recyclerView = binding.homeRestaurantList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RestaurantAdapter(RestaurantContent.ITEMS,this));




        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        Button add = binding.homeAdd;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.setReorderingAllowed(true);

                Fragment addRestaurants = new addRestaurants();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,addRestaurants);
                fragmentTransaction.addToBackStack(null);
// Add operations here

                fragmentTransaction.commit();
            }
        });





        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);

        Fragment addRestaurants = new addRestaurants();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,
                        RestaurantViewFragment.newInstance(String.valueOf(position)));
        fragmentTransaction.addToBackStack(null);
// Add operations here

        fragmentTransaction.commit();
    }


}
