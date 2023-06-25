package com.example.voting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Vote> votes;

    public RecyclerAdapter(List<Vote> votes) {
        this.votes = votes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vote vote = votes.get(position);
        holder.bind(vote);
    }

    @Override
    public int getItemCount() {
        return votes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView voteCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            voteCountTextView = itemView.findViewById(R.id.voteCountTextView);
        }

        public void bind(Vote vote) {
            voteCountTextView.setText(String.valueOf(vote.getVoteCount()));
        }
    }
}
