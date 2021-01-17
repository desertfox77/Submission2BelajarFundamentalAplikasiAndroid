package com.example.subdua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subdua.R;
import com.example.subdua.adapter.MainAdapter;
import com.example.subdua.api.APISettings;
import com.example.subdua.api.JSONPlaceHolderAPI;
import com.example.subdua.model.User;


import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FollowingFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_following, container, false);
        onViewFollowing(view);
        return view;
    }

    private void onViewFollowing(View view){
        recyclerView = view.findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        User up = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra("LEO");
        JSONPlaceHolderAPI jsonPlaceHolderAPI = APISettings.getRetrofit().create(JSONPlaceHolderAPI.class);
        retrofit2.Call<List<User>> call = jsonPlaceHolderAPI.followingFragment(Objects.requireNonNull(up).getLogin());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                MainAdapter mainAdapter = new MainAdapter(getContext(),response.body());
                recyclerView.setAdapter(mainAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}