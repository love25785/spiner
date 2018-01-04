package com.wl.spiner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<String> data;
    Spinner spinner;
    Spinner spinner2;
    TextView tv,tv2,tv3;
    EditText ed;
    ArrayAdapter<String> adapter;
    //↑為了讓 新增或刪除deta時 即時更新: adapter.notifyDataSetChanged();可行，需將adapter宣告在field，才可用adapter

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //↓可以這樣應用1
        tv=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        ed=(EditText)findViewById(R.id.editText);

        data=new ArrayList<>();
        data.add("aa11");
        data.add("bb22");
        data.add("cc33");

        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() //當"被選"的時候
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                tv.setText(data.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                ///////用偷懶方式寫spinner 以陣列方式寫寫死在資源的字串裡時，也是能從資源取得選取資料的方法↓
                String str[]=getResources().getStringArray(R.array.cities);
                tv3.setText(str[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    //↓可以這樣應用2
    public void click1(View v)
    {
        int pos=spinner.getSelectedItemPosition(); //取得 "被選的位置"
        tv2.setText(data.get(pos));
    }


    //↓可以這樣應用3 新增
    public void clickadd(View v)
    {
        data.add(ed.getText().toString());
        adapter.notifyDataSetChanged();//當data更新時，即時更新adapter
    }

    //↓可以這樣應用4 刪除
    public void clickDelete(View v)
    {
        data.remove(spinner.getSelectedItemPosition());
        adapter.notifyDataSetChanged(); //當data更新時，即時更新adapter
    }

    /*而另一種偷懶的，寫死的spinner : 直接在 資源res下的values下的strings.xml，加上: EX.
     <string-array name="cities">
        <item>台北</item>
        <item>台中</item>
        <item>台南</item>
        <item>高雄</item>
    </string-array>
     然後在layout 該spinner的屬性的entries 加上該陣列 EX. @array/cities 即可

     以陣列方式寫寫死在資源的字串裡時，也是能從"資源"取得選取資料的方法↓
     String str[]=getResources().getStringArray(R.array.cities);
    */


}
