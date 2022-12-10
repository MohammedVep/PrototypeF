package com.georgebrown.prototype1.ui.addRestaurants;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.georgebrown.prototype1.Model.Restaurant;
import com.georgebrown.prototype1.databinding.FragmentAddRestaurantsBinding;
import com.georgebrown.prototype1.placeholder.RestaurantContent;

public class addRestaurants extends Fragment {

    private AddRestaurantsViewModel mViewModel;

    private RestaurantContent restaurantContent;

    public static addRestaurants newInstance() {
        return new addRestaurants();
    }
    private FragmentAddRestaurantsBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        binding = FragmentAddRestaurantsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button add = binding.addRestaurantAdd;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(binding.addRestaurantName.getText());
                String address = String.valueOf(binding.addRestaurantAddress.getText());
                String city = String.valueOf(binding.addRestaurantCity.getText());
                String state = String.valueOf(binding.addRestaurantState.getText());
                String zip = String.valueOf(binding.addRestaurantZipCode.getText());
                String task = String.valueOf(binding.addRestaurantTasks.getText());
                String tags = String.valueOf(binding.addRestaurantTags.getText());
                String phone = String.valueOf(binding.addRestaurantPhone.getText());
                String email = String.valueOf(binding.addRestaurantEmail.getText());
                if(name.isEmpty()){name = "Default";}
                if(phone.isEmpty()){phone = "Default";}
                if(email.isEmpty()){email = "Default";}
                if(address.isEmpty()&&city.isEmpty()&& state.isEmpty() &&zip.isEmpty()){
                    AlertDialog.Builder alertDialog;
                    alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Fill The Address");
                    alertDialog.setMessage("The location is detected by Address. Please fill the valid Address");
                    alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    alertDialog.show();
                } else {
                    restaurantContent.addItem(new Restaurant(name,address,city,state,zip,task,tags,phone,email));
                    getFragmentManager().popBackStack();
                }

            }
        });
//        addRestaurantAdd
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddRestaurantsViewModel.class);
        // TODO: Use the ViewModel
    }

}