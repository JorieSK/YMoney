package com.example.ymoney;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import com.example.ymoney.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


   public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;


       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           binding = ActivityMainBinding.inflate(getLayoutInflater());
           setContentView(binding.getRoot());

           binding.navView.setOnItemSelectedListener(item -> {
               if (item.getItemId() == R.id.navigation_home) {
                   replaceFragment(new HomepFragment());
                   // Handle home navigation
               } else if (item.getItemId() == R.id.navigation_dashboard) {
                   replaceFragment(new AboutFragment());
                   // Handle dashboard navigation
               } else if (item.getItemId() == R.id.navigation_notifications) {
                   replaceFragment(new ContactFragment());
                   // Handle notifications navigation
               }
               return true;
           });

       }

      private void replaceFragment(Fragment fragment){
               FragmentManager fragmentManager= getSupportFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.frame_layout,fragment);
               fragmentTransaction.commit();

           }




   }


