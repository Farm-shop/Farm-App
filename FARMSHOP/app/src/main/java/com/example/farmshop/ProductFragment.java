//package com.example.farmshop;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProductFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class ProductFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private static final String ARG_PARAM3 = "param3";
//    private static final String ARG_PARAM4 = "param4";
//
//    // TODO: Rename and change types of parameters
//    private String mName;
//    private String mPrice;
//    private String mQuantity;
//    private String mImage;
//
//    public ProductFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProductFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProductFragment newInstance(String param1, String param2,String param3,String param4) {
//        ProductFragment fragment = new ProductFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        args.putString(ARG_PARAM3, param3);
//        args.putString(ARG_PARAM4, param4);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mName = getArguments().getString(ARG_PARAM1);
//            mPrice = getArguments().getString(ARG_PARAM2);
//            mQuantity = getArguments().getString(ARG_PARAM3);
//            mImage = getArguments().getString(ARG_PARAM4);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product, container, false);
//    }
//}