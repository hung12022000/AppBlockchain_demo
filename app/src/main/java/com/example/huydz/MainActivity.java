package com.example.huydz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.moshi.Moshi;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    Data data;
    TextView tv6;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = sdf.format(c.getTime());
    mainadapter       main_recycle_adapter;
    RecyclerView mainRecycleview;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv6=findViewById(R.id.textView4);
        tv6.setText(strDate);
        //call api
        // Thực thi request.

        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(Data.class);
        final JsonAdapter<Data> jsonAdapter = moshi.adapter(usersType);

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("https://help.ftx.com/api/v2/help_center/en-us/sections/4414741387924/articles.json")
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();



                final Data users = jsonAdapter.fromJson(json);
                //System.out.println(users.getArticles().get(0).getBody());
                for (articles newdt:users.getArticles()) {

                    newdt.setContent(regex(newdt.getBody(),1));
                    newdt.setImage(regex(newdt.getBody(),2));
                    newdt.setSubtitle1(regex(newdt.getBody(),3));
                    newdt.setSubtitle2(regex(newdt.getBody(),4));
                    newdt.setLitag(regex(newdt.getBody(),5));

                    //newdt.setSubtitle2(regex(newdt.getBody(),4));
                }
                for (articles newdt:users.getArticles()) {
                    System.out.println("");
                    System.out.println("");
                 System.out.println("noidung:"+newdt.getContent());
                    System.out.println("-"+newdt.getImage());
                    System.out.println("#"+newdt.getSubtitle1());
                    System.out.println("$"+newdt.getSubtitle2());
                    System.out.println(newdt.getLitag());
                    System.out.println("");
                    System.out.println("");
                }

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        setMainRecycleview(users.getArticles());
                    }
                });

            }
        });
        //end api
    }
    public void setMainRecycleview(List<articles> list){
        mainRecycleview=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mainRecycleview.setLayoutManager(layoutManager);
        main_recycle_adapter=new mainadapter(this,list);
        mainRecycleview.setAdapter(main_recycle_adapter);
    }
    public String regex(String body,int vt){
        Pattern pattern = null;
        if(vt==1) {
            pattern = Pattern.compile("</strong></p>\\n<p[^>]*>(<span[^>]*>)?(.*?)(</span>)?</p>");
        } else if (vt == 2) {
            pattern = Pattern.compile("<img[^>]+src=\"([^\">]+)\"");
        }
        else if (vt == 3) {
            pattern = Pattern.compile(">(FTX.*)</");
        }
        else if (vt == 4) {
            pattern = Pattern.compile("<p><strong>(.*?)</strong></p>");
        }
        else if (vt == 5){
            pattern = Pattern.compile("<ul>(.*?)</ul>");
        }
        //Matching the compiled pattern in the String
        Matcher matcher = pattern.matcher(body);
 if(vt==5){
     matcher = pattern.matcher(body.replaceAll("\n","\\n"));

 }
        //System.out.println(body.replaceAll("\n","\\n"));
        String result="";
        if (matcher.find()) {
            if (vt == 1) {
                result = matcher.group(2);

            }


         else if(vt==2){
              result = matcher.group(1);

         }
         else if(vt==3){
             result = matcher.group(1);

         }
         else if(vt==4){
             result = matcher.group(1);
         }
         else if(vt==5){
                result = matcher.group(1);
                Pattern g = Pattern.compile("n<li[^>]*>n?",
                        Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
                result=g.matcher(result).replaceAll("+");

                 g = Pattern.compile("</li>n?",
                        Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
                result=g.matcher(result).replaceAll("\n");
            }

            Pattern p = Pattern.compile("</?span[^>]*>",
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            result=p.matcher(result).replaceAll("");
//looai the a
            p = Pattern.compile("<a href=\"(.*?)\" target=\"_blank\" rel=\"noopener\">(.*?)</a>",
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            result=p.matcher(result).replaceAll("$2($1)");

            //LOAI THE FONT
            p = Pattern.compile("<font>(.*?)</font>",
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            result=p.matcher(result).replaceAll("$1");

            p = Pattern.compile("<strong>",
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            result=p.matcher(result).replaceAll("");
            p = Pattern.compile("</strong>",
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            result=p.matcher(result).replaceAll("");

           return result;

        }


        return "";

    }

}