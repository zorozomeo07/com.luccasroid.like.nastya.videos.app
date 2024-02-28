package com.luccasroid.like.nastya.videos.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.luccasroid.like.nastya.videos.app.Adapter.AppAdapter;
import com.luccasroid.like.nastya.videos.app.Adapter.AppLive;
import com.luccasroid.like.nastya.videos.app.Play;
import com.luccasroid.like.nastya.videos.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    String Api_Key="AIzaSyAWTKsPQggUZDwSzkzMuTKpBw8aX8FLQ68";
    String ID_Video="PLGxWQdzOwID7gHvM9jUAxMcdI08xlCDCS";
    ListView lview;
    AppAdapter adapter;
    List<AppLive> applist;
    String urlcode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        // url
        urlcode="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_Video+"&key="+Api_Key+"&maxResults=200";
        lview = (ListView) view.findViewById(R.id.list_home);
        applist =new ArrayList<>();

        Collections.shuffle(applist);
        adapter =new AppAdapter(requireContext(),R.layout.iteam_app, applist);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(requireContext(), Play.class);
                intent.putExtra("pl","a");
                intent.putExtra("id_video",applist.get(i).getIDvideo());
                intent.putExtra("name",applist.get(i).getTitle());
                intent.putExtra("position",String.valueOf(i));
                intent.putExtra("thumb",applist.get(i).getThumb());
               requireContext().startActivity(intent);

            }
        });
        Log.d("aaaa",urlcode);
        GetDataYoutube(urlcode);

        return view;
    }
    public void GetDataYoutube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    String title, url, idVideo;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject jsonSnippet = jsonObject.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThumb = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedia = jsonThumb.getJSONObject("medium");
                        url = jsonMedia.getString("url");

                        System.out.println(url);
                        JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceID.getString("videoId");
                        applist.add(new AppLive(title, url, idVideo));

//                        Toast.makeText(MainActivity.this, idVideo, Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}