package com.example.ymoney;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ymoney.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

   public class MainActivity extends AppCompatActivity {

       TextView dayTextView;
       private TextView timeTextView;
       private SimpleDateFormat timeFormat;
       private Handler handler;
       private Runnable updateTimeRunnable;
       private ImageButton exitButton;
       private boolean isLongPress = false;


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

           dayTextView = findViewById(R.id.DAYtextView);
           SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
           String currentDay = sdf.format(new Date());

           dayTextView.setText(currentDay);


           timeTextView = findViewById(R.id.textView4);
           timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
           handler = new Handler();

           updateTimeRunnable = new Runnable() {
               @Override
               public void run() {
                   updateTime();
                   handler.postDelayed(this, 1000); // Update every second (1000 milliseconds)
               }
           };
           exitButton = findViewById(R.id.imageButton6);
           exitButton.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   isLongPress = true;
                   Toast.makeText(MainActivity.this, "Long Pressed! Exiting...", Toast.LENGTH_SHORT).show();
                   finish();
                   return true;
               }
           });

           exitButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (!isLongPress) {
                       // Handle regular click event here
                       Toast.makeText(MainActivity.this, "Short Pressed", Toast.LENGTH_SHORT).show();
                   }
                   isLongPress = false;
               }
           });
       }


       @Override
       protected void onResume() {
           super.onResume();
           handler.post(updateTimeRunnable);
       }

       @Override
       protected void onPause() {
           super.onPause();
           handler.removeCallbacks(updateTimeRunnable);
       }
       private void updateTime() {
           Calendar calendar = Calendar.getInstance();
           String currentTime = timeFormat.format(calendar.getTime());
           timeTextView.setText(currentTime);
       }
      private void replaceFragment(Fragment fragment){
               FragmentManager fragmentManager= getSupportFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.frame_layout,fragment);
               fragmentTransaction.commit();

           }




   }




//       @Override
//       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//           switch (item.getItemId()) {
//               case R.id.navigation_home:
//                   // Navigate to the home layout
//                   setContentView(R.layout.fragment_home);
//                   return true;
//               // Add more cases for other menu items if needed
//           }
//           return false;
//       }

