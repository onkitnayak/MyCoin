package com.edevlop.mycoin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.edevlop.mycoin.Adapter.DataListAdapter;
import com.edevlop.mycoin.Database.AppDatabase;
import com.edevlop.mycoin.Database.DataModel;
import com.edevlop.mycoin.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DataListAdapter dataListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        AppDatabase db = AppDatabase.getDbInstance(HomeActivity.this);
        List<DataModel> dataList =db.userDao().getAllUsers();


        GridLayoutManager mLayoutManager = new GridLayoutManager(HomeActivity.this, 3);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);


        dataListAdapter = new DataListAdapter(this);
        recyclerView.setAdapter(dataListAdapter);

        // Log.i("DATAAA", String.valueOf(dataModelList));
        dataListAdapter.setUserList(dataList);


    }
}