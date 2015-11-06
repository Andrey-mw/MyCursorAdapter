package com.example.android.mycursoradapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private DBQueryHelper dbQueryHelper;
    private EditText search;
    private MyCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbQueryHelper = new DBQueryHelper(this);
        listView = (ListView) findViewById(R.id.listView);
        search = (EditText) findViewById(R.id.etSearch);

        adapter = new MyCursorAdapter(this, dbQueryHelper.getAll(), true);
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return dbQueryHelper.getFilteredItemsByName(constraint.toString());
            }
        });
        listView.setAdapter(adapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        dbQueryHelper.closeDB();
    }
}
