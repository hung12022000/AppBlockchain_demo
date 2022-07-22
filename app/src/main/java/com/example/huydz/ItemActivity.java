package com.example.huydz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemActivity extends Activity {
    TextView tv1,tv2,tv3,tv4,tv5;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       articles myObject = (articles) getIntent().getSerializableExtra("data");
        setContentView(R.layout.text_inside);
        tv1=findViewById(R.id.titlemain);
        tv2=findViewById(R.id.subtit1);
        tv3=findViewById(R.id.litag);
        tv4=findViewById(R.id.subtit2);
        tv5=findViewById(R.id.content);
        img=findViewById(R.id.ha);

        tv1.setText(myObject.getTitle());
        tv2.setText(myObject.getSubtitle1());
        tv3.setText(myObject.getLitag());
        tv4.setText(myObject.getSubtitle2());
        tv5.setText(myObject.getContent());
        Picasso.get().load(myObject.getImage()).into(img);


    }

}
