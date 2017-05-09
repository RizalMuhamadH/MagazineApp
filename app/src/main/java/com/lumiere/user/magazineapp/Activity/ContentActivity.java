package com.lumiere.user.magazineapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.lumiere.user.magazineapp.Adapter.ContentAdapter;
import com.lumiere.user.magazineapp.Model.Edition;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContentActivity extends AppCompatActivity {
    private static String TAG = "Edition Page";
    private GridView grid;
    private ContentAdapter adapter;

    private ImageView profile;
    private ImageView announ;

    private FrameLayout layout;

    private ArrayList<Edition> arrayList;

    private BroadcastReceiver receiver;

    private Snackbar snackbar;


    private Edition edisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        getSupportActionBar().hide();

        profile = (ImageView)findViewById(R.id.btn_profile);
        announ = (ImageView)findViewById(R.id.btn_announ);
        grid = (GridView)findViewById(R.id.grid_content);

        layout = (FrameLayout)findViewById(R.id.layout_library);
        arrayList = new ArrayList<>();

//        getAllEdition();

        String[] img ={"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"};
        for (int i=0;i<10;i++){
            edisi = new Edition("Edisi "+String.valueOf(i+1),"01-01-2017",img[i]);
            arrayList.add(edisi);
        }
        adapter = new ContentAdapter(arrayList,ContentActivity.this);
        grid.setAdapter(adapter);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContentActivity.this, CoverMagazineActivity.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        announ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this,AnnouncementActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("onTokenRefresh: ",refreshedToken);

//        receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                FirebaseMessaging.getInstance().subscribeToTopic("global");
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("firebase", 0);
//                String refreshedToken = pref.getString("regID", null);
//                Log.e("FCM: ", refreshedToken);
//            }
//        };
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("registrationComplete"));
//    }
//
//    @Override
//    protected void onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//        super.onPause();
//    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                Intent intent = new Intent(ContentActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
    private void getAllEdition(){
        StringRequest request = new StringRequest(Request.Method.GET, "http://10.0.2.2/CodeIgniter-2.2.6/edition_controller/getAllEdition",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            http://mobs.ayobandung.com/images-data/
//                            Log.e("Respone",response);
                            JSONObject jsonObject = new JSONObject(response);

                            Log.e(TAG + " Respone",jsonObject.toString());
                            String status = jsonObject.getString("status");
                            Log.e(TAG, status);

                            if (status.equals("success")){
                                Log.e(TAG, status);
                                JSONArray jsonData = jsonObject.getJSONArray("data");

                                Log.e(TAG, jsonData.toString());
                                String[] img ={"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"
                                        ,"http://www.wowkeren.com/images/events/ori/2011/07/20/captain-america-poster01.jpg"};
                                for (int i=0;i<jsonData.length();i++){
                                    JSONObject objectData = jsonData.getJSONObject(i);
                                    Log.e(TAG, objectData.toString());
                                    String date = objectData.getString("edition_date");
                                    Log.e(TAG, date);
                                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
                                    SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy");
                                    Date parse = input.parse(date);
                                    String dateParse = output.format(parse);
                                    Log.e(TAG, dateParse);
                                    edisi = new Edition("Edisi "+String.valueOf(i+1),dateParse,img[i]);
                                    arrayList.add(edisi);
                                }
                                adapter = new ContentAdapter(arrayList,ContentActivity.this);
                                grid.setAdapter(adapter);
                            }else {
                                Log.e(TAG, "gagal");
                            }

//                            status = jsonObject.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        Log.e(TAG + " Respone",response);
//                        dialog.dismiss();

                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
//                        Log.e(TAG + " Error",error.toString());
                        snackbar = Snackbar.make(layout,"Connection failed",Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
