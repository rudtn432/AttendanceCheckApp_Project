package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimeTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeTableFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TimeTableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeTableFragment newInstance(String param1, String param2) {
        TimeTableFragment fragment = new TimeTableFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        TextView tv = view.findViewById(R.id.testText);
        TextView tvMonday1 = view.findViewById(R.id.f00);
        TextView tvMonday2 = view.findViewById(R.id.s00);
        TextView tvMonday3 = view.findViewById(R.id.t00);
        TextView tvMonday4 = view.findViewById(R.id.f10);
        TextView tvMonday5 = view.findViewById(R.id.f20);
        TextView tvMonday6 = view.findViewById(R.id.s10);
        TextView tvMonday7 = view.findViewById(R.id.s20);
        TextView tvMonday8 = view.findViewById(R.id.e00);

        TextView tvTuesday1 = view.findViewById(R.id.f01);
        TextView tvTuesday2 = view.findViewById(R.id.s01);
        TextView tvTuesday3 = view.findViewById(R.id.t01);
        TextView tvTuesday4 = view.findViewById(R.id.f11);
        TextView tvTuesday5 = view.findViewById(R.id.f21);
        TextView tvTuesday6 = view.findViewById(R.id.s11);
        TextView tvTuesday7 = view.findViewById(R.id.s21);
        TextView tvTuesday8 = view.findViewById(R.id.e01);

        TextView tvWednesday1 = view.findViewById(R.id.f02);
        TextView tvWednesday2 = view.findViewById(R.id.s02);
        TextView tvWednesday3 = view.findViewById(R.id.t02);
        TextView tvWednesday4 = view.findViewById(R.id.f12);
        TextView tvWednesday5 = view.findViewById(R.id.f22);
        TextView tvWednesday6 = view.findViewById(R.id.s12);
        TextView tvWednesday7 = view.findViewById(R.id.s22);
        TextView tvWednesday8 = view.findViewById(R.id.e02);

        TextView tvThursday1 = view.findViewById(R.id.f03);
        TextView tvThursday2 = view.findViewById(R.id.s03);
        TextView tvThursday3 = view.findViewById(R.id.t03);
        TextView tvThursday4 = view.findViewById(R.id.f13);
        TextView tvThursday5 = view.findViewById(R.id.f23);
        TextView tvThursday6 = view.findViewById(R.id.s13);
        TextView tvThursday7 = view.findViewById(R.id.s23);
        TextView tvThursday8 = view.findViewById(R.id.e03);

        TextView tvFriday1 = view.findViewById(R.id.f04);
        TextView tvFriday2 = view.findViewById(R.id.s04);
        TextView tvFriday3 = view.findViewById(R.id.t04);
        TextView tvFriday4 = view.findViewById(R.id.f14);
        TextView tvFriday5 = view.findViewById(R.id.f24);
        TextView tvFriday6 = view.findViewById(R.id.s14);
        TextView tvFriday7 = view.findViewById(R.id.s24);
        TextView tvFriday8 = view.findViewById(R.id.e04);

        String ttext[]= new String[20];
        String tttext=null;
        String message = this.getArguments().getString("msg");
        int k=0;
        char[] array_word = new char[message.length()]; // 스트링을 담을 배열

        for(int i=0;i<array_word.length;i++){
            array_word[i]=(message.charAt(i));//스트링을 한글자씩 끊어 배열에 저장
        }

        for(int i=0;i<array_word.length;i++)
        {
            if(array_word[i]=='월')
            {
                i+=4;

                for(int j=0;j<8;j++)
                {
                    while (array_word[i] != '.' && array_word[i] != '"')
                    {
                        ttext[k] = String.valueOf(array_word[i]);
                        k++;
                        i++;
                    }

                    if(ttext[0]==null || ttext[0] == "")
                        tttext="";
                    else
                        tttext=String.join("", ttext);

                    switch (j){
                        case 0: tvMonday1.setText(tttext);
                            break;
                        case 1: tvMonday2.setText(tttext);
                            break;
                        case 2: tvMonday3.setText(tttext);
                            break;
                        case 3: tvMonday4.setText(tttext);
                            break;
                        case 4: tvMonday5.setText(tttext);
                            break;
                        case 5: tvMonday6.setText(tttext);
                            break;
                        case 6: tvMonday7.setText(tttext);
                            break;
                        case 7: tvMonday8.setText(tttext);
                            break;
                    }
                    i++;
                    k=0;
                    Arrays.fill(ttext,"");  // 초기화
                }
            }

            if(array_word[i]=='화')
            {
                i+=4;

                for(int j=0;j<8;j++)
                {
                    while (array_word[i] != '.' && array_word[i] != '"')
                    {
                        ttext[k] = String.valueOf(array_word[i]);
                        k++;
                        i++;
                    }

                    if(ttext[0]==null || ttext[0] == "")
                        tttext="";
                    else
                        tttext=String.join("", ttext);

                    switch (j){
                        case 0: tvTuesday1.setText(tttext);
                            break;
                        case 1: tvTuesday2.setText(tttext);
                            break;
                        case 2: tvTuesday3.setText(tttext);
                            break;
                        case 3: tvTuesday4.setText(tttext);
                            break;
                        case 4: tvTuesday5.setText(tttext);
                            break;
                        case 5: tvTuesday6.setText(tttext);
                            break;
                        case 6: tvTuesday7.setText(tttext);
                            break;
                        case 7: tvTuesday8.setText(tttext);
                            break;
                    }
                    i++;
                    k=0;
                    Arrays.fill(ttext,"");  // 초기화
                }
            }

            if(array_word[i]=='수')
            {
                i+=4;

                for(int j=0;j<8;j++)
                {
                    while (array_word[i] != '.' && array_word[i] != '"')
                    {
                        ttext[k] = String.valueOf(array_word[i]);
                        k++;
                        i++;
                    }

                    if(ttext[0]==null || ttext[0] == "")
                        tttext="";
                    else
                        tttext=String.join("", ttext);

                    switch (j){
                        case 0: tvWednesday1.setText(tttext);
                            break;
                        case 1: tvWednesday2.setText(tttext);
                            break;
                        case 2: tvWednesday3.setText(tttext);
                            break;
                        case 3: tvWednesday4.setText(tttext);
                            break;
                        case 4: tvWednesday5.setText(tttext);
                            break;
                        case 5: tvWednesday6.setText(tttext);
                            break;
                        case 6: tvWednesday7.setText(tttext);
                            break;
                        case 7: tvWednesday8.setText(tttext);
                            break;
                    }
                    i++;
                    k=0;
                    Arrays.fill(ttext,"");  // 초기화
                }
            }

            if(array_word[i]=='목')
            {
                i+=4;

                for(int j=0;j<8;j++)
                {
                    while (array_word[i] != '.' && array_word[i] != '"')
                    {
                        ttext[k] = String.valueOf(array_word[i]);
                        k++;
                        i++;
                    }

                    if(ttext[0]==null || ttext[0] == "")
                        tttext="";
                    else
                        tttext=String.join("", ttext);

                    switch (j){
                        case 0: tvThursday1.setText(tttext);
                            break;
                        case 1: tvThursday2.setText(tttext);
                            break;
                        case 2: tvThursday3.setText(tttext);
                            break;
                        case 3: tvThursday4.setText(tttext);
                            break;
                        case 4: tvThursday5.setText(tttext);
                            break;
                        case 5: tvThursday6.setText(tttext);
                            break;
                        case 6: tvThursday7.setText(tttext);
                            break;
                        case 7: tvThursday8.setText(tttext);
                            break;
                    }
                    i++;
                    k=0;
                    Arrays.fill(ttext,"");  // 초기화
                }
            }

            if(array_word[i]=='금')
            {
                i+=4;

                for(int j=0;j<8;j++)
                {
                    while (array_word[i] != '.' && array_word[i] != '"')
                    {
                        ttext[k] = String.valueOf(array_word[i]);
                        k++;
                        i++;
                    }

                    if(ttext[0]==null || ttext[0] == "")
                        tttext="";
                    else
                        tttext=String.join("", ttext);

                    switch (j){
                        case 0: tvFriday1.setText(tttext);
                            break;
                        case 1: tvFriday2.setText(tttext);
                            break;
                        case 2: tvFriday3.setText(tttext);
                            break;
                        case 3: tvFriday4.setText(tttext);
                            break;
                        case 4: tvFriday5.setText(tttext);
                            break;
                        case 5: tvFriday6.setText(tttext);
                            break;
                        case 6: tvFriday7.setText(tttext);
                            break;
                        case 7: tvFriday8.setText(tttext);
                            break;
                    }
                    i++;
                    k=0;
                    Arrays.fill(ttext,"");  // 초기화
                }
            }
        }
        // Inflate the layout for this fragment
        return view;
    }
}