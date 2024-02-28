package com.luccasroid.like.nastya.videos.app.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.luccasroid.like.nastya.videos.app.Adapter.FavouriteAdapter;
import com.luccasroid.like.nastya.videos.app.DataBase.DataApp;
import com.luccasroid.like.nastya.videos.app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
    DataApp dataApp;
    ArrayList<String> idList,idvideoList,namelist,thumbList;
    RecyclerView relayLove;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favourite, container, false);
        // Inflate the layout for this fragment
        dataApp=new DataApp(requireContext());
        idList=new ArrayList<>();
        idvideoList=new ArrayList<>();
        namelist=new ArrayList<>();
        thumbList=new ArrayList<>();
        ReadData();
        if (idList.isEmpty()) {
            Toast.makeText(requireContext(), "NoData", Toast.LENGTH_SHORT).show();
        } else {
            // Hiển thị dữ liệu trong RecyclerView
            FavouriteAdapter loveAdapter = new FavouriteAdapter(requireContext(), idList, idvideoList, namelist,thumbList);
            relayLove = view.findViewById(R.id.listFavourites);
            relayLove.setAdapter(loveAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 1);
            relayLove.setLayoutManager(layoutManager);
        }

        return view;
    }
    public void ReadData(){
        Cursor cursor = dataApp.readData();

        // Xử lý dữ liệu từ Cursor và thêm vào danh sách
        while (cursor.moveToNext()) {
            idList.add(cursor.getString(0));
            idvideoList.add(cursor.getString(1));
            namelist.add(cursor.getString(2));
            thumbList.add(cursor.getString(3));
        }

        // Đóng cursor sau khi sử dụng
        cursor.close();
    }
}