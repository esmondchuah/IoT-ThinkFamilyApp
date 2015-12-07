package com.mycompany.thinkfamily;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SuggestionsFragment extends Fragment {

    private List<Suggestion> suggestion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(suggestion);
        rv.setAdapter(adapter);
        return view;
    }

    private void initializeData(){
        suggestion = new ArrayList<>();
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.dintaifung));
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.mahjong));
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.chinatown));
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.teachat));
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.taichi));
        suggestion.add(new Suggestion("[Enter description here]", "[Enter description here]", R.drawable.cooking));
    }
}

class Suggestion {
    String description1;
    String description2;
    int photoId;

    Suggestion(String description1, String description2, int photoId) {
        this.description1 = description1;
        this.description2 = description2;
        this.photoId = photoId;
    }
}

class RVAdapter extends RecyclerView.Adapter<RVAdapter.SuggestionViewHolder>{

    List<Suggestion> suggestion;

    RVAdapter(List<Suggestion> suggestion){
        this.suggestion = suggestion;
    }

    @Override
    public int getItemCount() {
        return suggestion.size();
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_row, viewGroup, false);
        SuggestionViewHolder pvh = new SuggestionViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder suggestionViewHolder, int i) {
        suggestionViewHolder.description1.setText(suggestion.get(i).description1);
        suggestionViewHolder.description2.setText(suggestion.get(i).description2);
        suggestionViewHolder.suggestionPhoto.setImageResource(suggestion.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class SuggestionViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView description1;
        TextView description2;
        ImageView suggestionPhoto;

        SuggestionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            description1 = (TextView)itemView.findViewById(R.id.description1);
            description2 = (TextView)itemView.findViewById(R.id.description2);
            suggestionPhoto = (ImageView)itemView.findViewById(R.id.suggestion_photo);
        }


    }

}