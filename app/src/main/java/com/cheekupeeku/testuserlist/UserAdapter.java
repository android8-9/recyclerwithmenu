package com.cheekupeeku.testuserlist;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.cheekupeeku.testuserlist.databinding.ItemListBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<User>al;
    public UserAdapter(Context context,ArrayList<User>al){
        this.context =context;
        this.al = al;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
     User user = al.get(position);
     holder.binding.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
    ItemListBinding binding;
    public UserViewHolder(ItemListBinding binding){
        super(binding.getRoot());
        this.binding = binding;
        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,binding.ivMenu);
                Menu menu = popupMenu.getMenu();
                menu.add("View");
                menu.add("Delete");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        if(title.equals("Delete")){
                            AlertDialog.Builder ab = new AlertDialog.Builder(context);
                            ab.setTitle("Deleting user");
                            ab.setMessage("Are you sure ?");
                            ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int position = getAdapterPosition();
                                    al.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            ab.setNegativeButton("No",null);
                            ab.show();
                        }
                        else if(title.equals("View")){

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
  }
}

