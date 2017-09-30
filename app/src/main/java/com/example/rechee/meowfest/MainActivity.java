package com.example.rechee.meowfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rechee.meowfest.models.Cat;
import com.example.rechee.meowfest.models.CatListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatListener {

    private CatViewModel viewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Cat> cats;
    private CatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewModel = new CatViewModel(this);

        viewModel.getCats(0);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView_cats);
        layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        this.cats = new ArrayList<>();
        this.adapter = new CatListAdapter(cats, this);
        this.recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void catsReceived(List<Cat> catsReceived) {
        this.cats.addAll(catsReceived);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void catRetrieveFailure(String error) {

    }
}
