package com.example.recipedbnew.ui.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipedbnew.AsyncResponse;
import com.example.recipedbnew.BackgroundConnector;
import com.example.recipedbnew.R;
import com.example.recipedbnew.databinding.FragmentSearchBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    TextView txt1;
    Button searchBtn;
    Button searchBtn_cuisine;
    EditText titleTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View root = binding.getRoot();
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);



        txt1 = (TextView) root.findViewById(R.id.txt1);
        titleTxt = (EditText) root.findViewById(R.id.TitleTxt);
        Log.d("here", String.valueOf(txt1.getText()));
        searchBtn = (Button) root.findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txt1.setText("done");
            }
        });

        searchBtn_cuisine = (Button) root.findViewById(R.id.searchBtn_cuisine);
        searchBtn_cuisine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BackgroundConnector bgconnector = new BackgroundConnector(new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        String op = output;
                        JSONObject jObject = null;
                        try {
                            jObject = new JSONObject(op);
                            txt1.setText(jObject.getString("recipe_title"));
                            Log.d("here",op);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                bgconnector.execute("day");
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}