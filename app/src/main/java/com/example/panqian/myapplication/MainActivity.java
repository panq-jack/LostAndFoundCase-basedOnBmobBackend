package com.example.panqian.myapplication;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.panqian.myapplication.adapters.MyAdapter;
import com.example.panqian.myapplication.model.Found;
import com.example.panqian.myapplication.model.Lost;
import com.example.panqian.myapplication.views.MyDialogFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity {

    private MyAdapter myAdapter;
    private String[] spinner_data;
    private View dialogView;

    private int pos;
    @InjectView(R.id.spinner)
    Spinner spinner;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick(R.id.iv_right)
    public void plus(View view){
        showDialog(pos);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setUpSpinner();
        initView();
    }
    private void setUpSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.adapters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                refreshView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initView(){
        spinner_data=getResources().getStringArray(R.array.adapters);
        myAdapter=new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
    private void refreshView(int pos){
        if (0==pos){
            BmobQuery<Lost> query=new BmobQuery<>();
            query.order("-createdAt");
            query.findObjects(this, new FindListener<Lost>() {
                @Override
                public void onSuccess(List<Lost> list) {
                    myAdapter.setData(list,true);
                }

                @Override
                public void onError(int i, String s) {
                    log(i+"    "+s);
                }
            });
        }else if (1==pos){
            BmobQuery<Found> query=new BmobQuery<>();
            query.order("-createdAt");
            query.findObjects(this, new FindListener<Found>() {
                @Override
                public void onSuccess(List<Found> list) {
                    myAdapter.setData(list,true);
                }

                @Override
                public void onError(int i, String s) {
                    log(i+"    "+s);
                }
            });
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return MyDialogFactory.getInstance().createDialog(this,id,null);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }
}
