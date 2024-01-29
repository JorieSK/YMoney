package com.example.ymoney;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomepFragment extends Fragment {

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

        return view;
    }

    // Method to open a URL using CustomTabsIntent
    private void openUrl(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
    }

}