package com.example.farmshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listOrderFarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listOrderFarmFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5= "param5";
    // TODO: Rename and change types of parameters
    private String mUserName;
    private String mOrderImg;
    private String mOrderPrice;
    private String mOrderQuantity;
    private String mProductTitle;





    public listOrderFarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @param param3 Parameter 3.
     * @param param4 Parameter 4.
     * @param param5 Parameter 5.
     * @return A new instance of fragment listOrderFarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listOrderFarmFragment newInstance(String param1, String param2 ,String param3 ,String param4,String param5) {

        listOrderFarmFragment fragment = new listOrderFarmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserName = getArguments().getString(ARG_PARAM1);
            mOrderImg = getArguments().getString(ARG_PARAM2);
            mOrderPrice =getArguments().getString(ARG_PARAM3);
            mOrderQuantity =  getArguments().getString(ARG_PARAM4);
            mProductTitle=getArguments().getString(ARG_PARAM5);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_order_farm, container, false);
    }
}