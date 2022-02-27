package com.example.recipedbnew.ui.gallery;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipedbnew.R;
import com.example.recipedbnew.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    TextView txt1;
    Button searchBtn;
    CardView crdView;

    LinearLayout cusine_layout;
    LinearLayout cusine_details;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View root = binding.getRoot();
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cusine_layout=root.findViewById(R.id.cusine_layout);
        cusine_details=root.findViewById(R.id.cusine_details);

        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);



        txt1 = (TextView) root.findViewById(R.id.txt1);
        Log.d("here", String.valueOf(txt1.getText()));
        searchBtn = (Button) root.findViewById(R.id.searchBtn);
        crdView= (CardView) root.findViewById(R.id.card1);
        crdView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                int v= (cusine_details.getVisibility()==View.GONE )? View.VISIBLE: View.GONE;
                TransitionManager.beginDelayedTransition(cusine_layout,new AutoTransition());
                cusine_details.setVisibility(v);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txt1.setText("done");
            }
        });

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    public void expand1(View view){

        int v= (cusine_details.getVisibility()==View.GONE )? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(cusine_layout,new AutoTransition());
        cusine_details.setVisibility(v);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}