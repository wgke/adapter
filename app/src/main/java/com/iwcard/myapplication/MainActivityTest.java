package com.iwcard.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wgke.adapter.cell.BindCellAdapter;
import com.wgke.adapter.cell.Cell;
import com.wgke.adapter.cell.CellAdapter;
import com.wgke.adapter.cell.DataBinder;
import com.wgke.adapter.cell.MultiCell;
import com.wgke.viewholder.RVViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.listview);
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7");
        CellAdapter cellAdapter = new CellAdapter(this);
        BindCellAdapter bindCellAdapter = new BindCellAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setAdapter(cellAdapter);
        rv.setAdapter(bindCellAdapter);
        List<Cell> cells = new ArrayList<>();
        //List<StrModel> strModels = new ArrayList<>();
        for (String str : list) {
            cells.add(MultiCell.convert(R.layout.layout_cell_t, new StrModel("标题" + str, "详情：" + str), (holder, var2) -> {
                holder.initViewDataBinding(BR.model, var2);
                holder.getView().setOnClickListener(view -> {
                    Toast.makeText(view.getContext(), var2.getTitle(), Toast.LENGTH_SHORT).show();
                });
            }));
        }
        bindCellAdapter.setDataList(cells);
    }
}
