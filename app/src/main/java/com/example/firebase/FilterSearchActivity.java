package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterSearchActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recycler_filter_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_search);


        recycler_filter_search =(RecyclerView) findViewById(R.id.recycler_filter_search);
        recycler_filter_search.setHasFixedSize(true);
        recycler_filter_search.setLayoutManager( new GridLayoutManager(this,2));

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.inflateMenu((R.menu.main_menu));
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public boolean onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemid()){
                    case R.id.action_filter:
                        showFiltersDialog();
                        break;
                    case R.id.action_search:
                        showSearchDialog();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void showSearchDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FilterSearchActivity.this);
        alertDialog.setTitle("Search ");
        LayoutInflater inflater = this.getLayoutInflater();
        View filter_layout = inflater.inflate(R.layout.dialog_search,null);
        EditText edt_search = (EditText)search_layout.findViewbyId(R.id.edt_search);
        alertDialog.setView(search_layout);
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        alertDialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fetchSearchComic(edt_search.getText().toString());
                
            }
        });
        alertDialog.show();



    }

    private void fetchSearchComic(String query) {

        List<Comic> comic_search = new ArrayList<>();
        for (Comic comic:Common.comicList){
            if(comic.Name.contains(query))
                comic_search.add(comic);
        }
        if(comic_search.size() > 0)
            recycler_filter_search.setAdapter(new MyComicAdapter(getBaseContext(),comic_search));
        else
            Toast.makeText(this, "No result", Toast.LENGTH_SHORT).show();

    }

    private void showFiltersDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FilterSearchActivity.this);
        alertDialog.setTitle("Select Category");
        LayoutInflater inflater = this.getLayoutInflater();
        View filter_layout = inflater.inflate(R.layout.dialog_options,null);

        AutoCompleteTextView txt_category = (AutoCompleteTextView) filter_layout.findViewById(R.id.txt_category);
        ChipGroup chipGroup = (ChipGroup) filter_layout.findViewById(R.id.chipGroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item,Common.categories);
        txt_category.setAdapter(adapter);

        txt_category.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i ,long l){
                txt_category.setText("");
                Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
                chip.setText(((TextView)view).getText());
                chip.setOnCloseIconClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        chipGroup.removeView(view);
                    }
                });
                chipGroup.addView(chip);
            }
        });

        alertDialog.setView(filter_layout);
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                List<String> filter_key = new ArrayList<>();
                StringBuilder filter_query = new StringBuilder("");
                for(int j=0;j<chipGroup.getChildCount();j++){
                    Chip chip = (Chip)chipGroup.getChildAt(j);
                    filter_key.add(chip.getText().toString());
                }
                Collection.sort(filter_key);
                for (String key:filter_key){
                    filter_query.append(key).append(",");
                }
                filter_query.setLength(filter_query.length()-1);
                fetchFilterCategory(filter_query.toString());
            }
        });
        alertDialog.show();

    }


    private void fetchFilterCategory(String toString) {

        List<Comic> comic_filtered = new ArrayList<>();
        for(Comic comic:Common.comicList){
            if(comic.Category != null) {
                if (comic.Category.contains(query)) {
                    comic_filtered.add(comic);

                }
            }
            if(comic_filtered.size() > 0)
            recycler_filter_search.setAdapter(new MyComicAdapter(getBaseContext(),comic_filtered));
            else
                Toast.makeText(this, "No result", Toast.LENGTH_SHORT).show();
        }
    }
}