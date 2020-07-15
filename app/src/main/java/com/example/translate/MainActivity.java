package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton b1;
    TextView stext;
    String sl = "English";
    String tl = "English";

    ImageButton b2;
    TextView ttext;
    EditText edit_input_txt;
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
                        sl = "" + menuItem.getTitle();
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
                        tl = "" + menuItem.getTitle();
                        ttext.setText(getResources().getResourceName(menuItem.getItemId()).split("\\/")[1]);
                        return true;
                    }
                });
                popupmenu.show();
            }
        });

        edit_input_txt = (EditText)findViewById(R.id.input_text);
        String input_txt = edit_input_txt.getText().toString();


    }




}