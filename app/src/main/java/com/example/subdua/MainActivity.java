package com.example.subdua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.subdua.adapter.MainAdapter;
import com.example.subdua.api.APISettings;
import com.example.subdua.api.JSONPlaceHolderAPI;
import com.example.subdua.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_main);
        recyclerView = findViewById(R.id.rv_main);
        searchView = findViewById(R.id.sv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String username) {
                loading(true);
                searchData(username);
                return true;
            }
        });
    }

    private void loading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainhomemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_language) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void searchData(String username){
        JSONPlaceHolderAPI jsonPlaceHolderAPI = APISettings.getRetrofit().create(JSONPlaceHolderAPI.class);
        Call<RetrofitResponse> call = jsonPlaceHolderAPI.searchUserData(username);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {

                if (response.isSuccessful()){
                    MainAdapter mainAdapter = new MainAdapter(getApplicationContext(),response.body().getItems());
                    recyclerView.setAdapter(mainAdapter);
                    loading(false);
                }
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Connection!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
