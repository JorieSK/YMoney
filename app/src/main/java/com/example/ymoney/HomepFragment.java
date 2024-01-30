package com.example.ymoney;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomepFragment extends Fragment {
    TextView dayTextView;
    private TextView timeTextView;
    private SimpleDateFormat timeFormat;
    private Handler handler;
    private Runnable updateTimeRunnable;
    private ImageButton exitButton;
    private boolean isLongPress = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomepFragment newInstance(String param1, String param2) {
        HomepFragment fragment = new HomepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homep, container, false);

        // Find the buttons in the inflated layout
        Button zakatButton = view.findViewById(R.id.ZAKAT);
        Button taxButton = view.findViewById(R.id.TAX);
        Button currencyButton = view.findViewById(R.id.CURRENCY);

        // Set click listeners for the buttons
        zakatButton.setOnClickListener(v -> openUrl("https://alhanouff21.github.io/YMoney-Website/Zakat-Calculation.html"));
        taxButton.setOnClickListener(v -> openUrl("https://alhanouff21.github.io/YMoney-Website/Tax-Calculation.html"));
        currencyButton.setOnClickListener(v -> openUrl("https://alhanouff21.github.io/YMoney-Website/Currency-Exchange.html"));



        dayTextView = view.findViewById(R.id.DAYtextView);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String currentDay = sdf.format(new Date());
        dayTextView.setText("Today Is " + currentDay);

        timeTextView = view.findViewById(R.id.textView4);
        timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        handler = new Handler();

        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000); // Update every second (1000 milliseconds)
            }
        };

        exitButton = view.findViewById(R.id.imageButton6);
        exitButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongPress = true;
                Toast.makeText(requireActivity(), "Long Pressed! Exiting...", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
                return true;
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLongPress) {
                    // Handle regular click event here
                    Toast.makeText(requireActivity(), "Short Pressed", Toast.LENGTH_SHORT).show();
                }
                isLongPress = false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(updateTimeRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        String currentTime = timeFormat.format(calendar.getTime());
        timeTextView.setText("The Time Now " + currentTime);
    }

    // Method to open a URL using CustomTabsIntent
    private void openUrl(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
    }
}