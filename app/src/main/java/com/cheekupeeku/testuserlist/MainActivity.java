package com.cheekupeeku.testuserlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cheekupeeku.testuserlist.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<User>al;
    UserAdapter adapter;
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
        adapter = new UserAdapter(this,al);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new DeleteOnSwipe());
        helper.attachToRecyclerView(binding.rv);
    }
    class DeleteOnSwipe extends ItemTouchHelper.SimpleCallback{
        public DeleteOnSwipe(){
            super(0,ItemTouchHelper.LEFT);
        }
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
            ab.setTitle("Deleting item");
            ab.setMessage("Are you sure ?");
            ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int position = viewHolder.getAdapterPosition();
                    User user = al.get(position);
                    al.remove(position);
                    adapter.notifyDataSetChanged();
                    //Snackbar.make(binding.rv,"Item deleted",Snackbar.LENGTH_LONG).show();
                    Snackbar sb = Snackbar.make(binding.rv,"Item deleted",Snackbar.LENGTH_LONG);
                    sb.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                             al.add(position,user);
                             adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Item recovered", Toast.LENGTH_SHORT).show();
                        }
                    });
                    sb.show();
                }
            });
            ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyDataSetChanged();
                }
            });
            ab.show();
        }
    }
}