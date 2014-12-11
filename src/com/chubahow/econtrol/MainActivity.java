package com.chubahow.econtrol;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;



public class MainActivity extends Activity {

    private SimpleAdapter mySimpleAdapter = null;
    private ListView myBTListView = null;
    private Spinner myBTSpinner = null;
    private List<Map<String,Object>> myBTList = null;

    private Button btn_add = null;
    private Button btn_del = null;
    private EditText edt_name = null;
    private EditText edt_addr = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myBTListView = (ListView) findViewById(R.id.listView_1);// find ListView
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_del = (Button) findViewById(R.id.btn_del);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_addr = (EditText) findViewById(R.id.edt_address);

        // set button click listeners
        // add one item to the list
        btn_add.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("T", "Add is pressed");

                // check the name and address are empty or not
                if(("".equals(edt_name.getText().toString().trim()))
                        || ("".equals(edt_name.getText().toString().trim())))//trim() 的意思是說，將 toString() 回傳的物件去掉空格後剩下的東西
                {
                    Toast.makeText(v.getContext(), "Please fill name and address", Toast.LENGTH_LONG).show();
                    return;
                }


                Map<String,Object> map = new HashMap<String, Object>();

                map.put("name", edt_name.getText().toString());
                map.put("address", edt_addr.getText().toString());
                myBTList.add(map);
                mySimpleAdapter.notifyDataSetChanged();// notify ListView that one item is updated.

                edt_name.setText("");
                edt_addr.setText("");
            }
        });

        //del the last one item of the list
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size;
                Map<String, Object> map = null;

                Log.v("T", "Del is pressed");

                if(!myBTList.isEmpty())
                {
                    size = myBTList.size();
                    Log.v("T", "myList has " + size + " items.");

                    // remove the last one item
                    map = myBTList.remove(size - 1);

                    // toast the "name" and "address" of the removed item
                    Toast.makeText(v.getContext(),
                            "List name:" + map.get("name") + ", addr: " +
                                    map.get("address"),
                            Toast.LENGTH_LONG).show();

                    mySimpleAdapter.notifyDataSetChanged();
                }
                else
                {
                    Log.v("T", "myList is empty");
                    Toast.makeText(v.getContext(), "List Empty", Toast.LENGTH_LONG).show();
                }
            }
        });


        // -*- generate a workable ListView widget -*-
        // 1. get a List obj
        myBTList = generateList();

        // 2. assign List object to SimpleAdapter object
        mySimpleAdapter = new SimpleAdapter(this,
                myBTList,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "address"},
                new int[]{android.R.id.text1, android.R.id.text2});

        // 3. assign adapter to ListView object
        myBTListView.setAdapter(mySimpleAdapter);

    }


    // Create an empty ArrayList object
    private List<Map<String,Object>> generateList()
    {
        List<Map<String,Object>> tmpList = new ArrayList<Map<String, Object>>() ;
/*
        //建立 MAP，將資料塞進 MAP 後，再將該 MAP 塞進 List 物件，這個 List 物件將包含 MAP 型態的資料
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "Jack");
        map.put("address", "Taiwan");
        tmpList.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Tom");
        map.put("address", "Japan");
        tmpList.add(map);
*/
        return tmpList;
    }
}