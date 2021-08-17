package com.example.wolny.Fragment.Main.MyJob;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.wolny.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class EmployerFragment extends Fragment {

    TabLayout tabLayout;
    View mView;
    FragmentContainerView fragmentContainerView;

    private static final String AS = "employer";

    public EmployerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_employer, container, false);

        tabLayout = mView.findViewById(R.id.tabLayout);
        fragmentContainerView = mView.findViewById(R.id.fragment_container_view);

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Open"));
        tabLayout.addTab(tabLayout.newTab().setText("Progress"));
        tabLayout.addTab(tabLayout.newTab().setText("Past"));

        TabLayout.Tab tab = tabLayout.getTabAt(2);
        assert tab != null;
        tab.select();
        setFragment(Objects.requireNonNull(tab.getText()).toString(), AS);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFragment(Objects.requireNonNull(tab.getText()).toString(), AS);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return mView;
    }

    public void setFragment(String status, String as) {
        Bundle bundle = new Bundle();

        bundle.putString("as", as);
        bundle.putString("status", status);
        getChildFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, ListFragment.class, bundle)
                .commit();
    }
}