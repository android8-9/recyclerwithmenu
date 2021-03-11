package com.cheekupeeku.testuserlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.cheekupeeku.testuserlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<User>al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        al = new ArrayList<>();
        al.add(new User("Cheeku"));
        al.add(new User("Peeku"));
        al.add(new User("Leesha"));
        al.add(new User("Neesha"));
        al.add(new User("Ishu"));
        al.add(new User("Dishu"));
        al.add(new User("Rahil"));
        al.add(new User("Sahil"));
        al.add(new User("Sontu"));
        al.add(new User("Montu"));
        al.add(new User("Atul"));
        al.add(new User("Ankita"));
        UserAdapter adapter = new UserAdapter(this,al);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
    }
}