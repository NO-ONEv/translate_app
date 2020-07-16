package com.example.translate;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {
    ImageButton b1;
    TextView stext;
    String sl = "en";
    String tl = "en";

    ImageButton b2;
    TextView ttext;

    EditText edit_input_txt;
    String inp = "";

    TextView edit_output_txt;
    String out = "";

    Button b3;
    private static final String TAG = "MainActivity";

    private JsonInterfaceApi jsonInterfaceApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = (ImageButton)findViewById(R.id.imageButton1);
        stext = (TextView)findViewById(R.id.slText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupmenu = new PopupMenu(MainActivity.this,b1);
                popupmenu.getMenuInflater().inflate(R.menu.popup_menu, popupmenu.getMenu());
                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this,"" + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        stext.setText(menuItem.getTitle());
                        sl = getResources().getResourceName(menuItem.getItemId()).split("\\/")[1];
                        return true;
                    }
                });
                popupmenu.show();
            }
        });

        b2 = (ImageButton)findViewById(R.id.imageButton2);
        ttext = (TextView)findViewById(R.id.tlText);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupmenu = new PopupMenu(MainActivity.this,b2);
                popupmenu.getMenuInflater().inflate(R.menu.popup_menu, popupmenu.getMenu());
                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this,"" + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        //ttext.setText(menuItem.getTitle());
                        tl = getResources().getResourceName(menuItem.getItemId()).split("\\/")[1];
                        ttext.setText(getResources().getResourceName(menuItem.getItemId()).split("\\/")[1]);
                        return true;
                    }
                });
                popupmenu.show();
            }
        });

        b3 = (Button)findViewById(R.id.translateit);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAns();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.google.com/translate_a/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        jsonInterfaceApi = retrofit.create(JsonInterfaceApi.class);
        Log.d(TAG, "hi vinee");
        System.out.println("hiiiiiiiiiii");

    }

    private void getAns() {
        //sl={sl}&tl={tl}&q={q}

        edit_input_txt = (EditText)findViewById(R.id.input_text);
        inp = edit_input_txt.getText().toString();

        edit_output_txt = (TextView)findViewById(R.id.output_text);

        if(!inp.equals("")) {
            Map<String, String> f = new HashMap<>();
            List<String> vals = new ArrayList<String>();
            vals.add("t");
            vals.add("ld");
            vals.add("qca");
            vals.add("rm");
            vals.add("bd");
            f.put("client", "at");
            f.put("dj", "1");
            f.put("hl", "%25s");
            f.put("ie", "UTF-8");
            f.put("oe", "UTF-8");
            f.put("inputm", "2");
            f.put("otf", "2");
            f.put("iid", "1dd3b944-fa62-4b55-b330-74909a99969e");
            f.put("sl", sl);
            f.put("tl", tl);
            f.put("q", inp);
            Call<String> call = jsonInterfaceApi.get(vals,f);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "CAME HERE 11");
                        return;
                    }
                    String sent = "";
                    String val = response.body();
                    JSONObject obj = null;
                    try {
                        JSONObject json = new JSONObject(val);
                        //System.out.println(json.get("sentences"));
                        JSONArray jsonArray = (JSONArray)json.get("sentences");
                        obj = jsonArray.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(obj.get("trans"));
                        out = obj.get("trans").toString();
                        edit_output_txt.setText(out);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "CAME HERE vinee" + sent);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    String k = t.getMessage();
                    Log.d(TAG, "OUCH" + k);
                }
            });
        }
    }




}