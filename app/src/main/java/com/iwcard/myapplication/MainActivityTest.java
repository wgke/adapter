package com.iwcard.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wake.recyclerView.adapter.cell.Cell;
import com.wake.recyclerView.adapter.cell.CellAdapter;
import com.wake.recyclerView.adapter.cell.DataBinder;
import com.wake.recyclerView.adapter.cell.MultiCell;
import com.wake.recyclerView.viewholder.RVViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.listview);
        List<String> list = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        CellAdapter cellAdapter = new CellAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(cellAdapter);
        List<Cell> cells = new ArrayList<>();
        for (String str : list) {
            cells.add(MultiCell.convert(R.layout.layout_cell_t, "", new DataBinder<String>() {
                @Override
                public void bindData(RVViewHolder var1, String var2) {

                }
            }));
        }
        cellAdapter.setDataList(cells);
    }
}
