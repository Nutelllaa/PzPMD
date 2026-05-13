package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notesapp.databinding.ItemSubNoteBinding;
import java.util.List;

public class SubNotesListAdapter extends RecyclerView.Adapter<SubNotesListAdapter.SubNoteViewHolder> {
    private final List<SubNote> subNotes;

    public SubNotesListAdapter(List<SubNote> subNotes) {
        this.subNotes = subNotes;
    }

    @NonNull
    @Override
    public SubNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSubNoteBinding binding = ItemSubNoteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new SubNoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubNoteViewHolder holder, int position) {
        holder.setSubNote(subNotes.get(position));
    }

    @Override
    public int getItemCount() { return subNotes.size(); }

    static class SubNoteViewHolder extends RecyclerView.ViewHolder {
        private final ItemSubNoteBinding binding;

        public SubNoteViewHolder(@NonNull ItemSubNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setSubNote(SubNote subNote) {
            binding.tvSubTitle.setText(subNote.getTitle());
            binding.checkSubCompleted.setOnCheckedChangeListener(null);
            binding.checkSubCompleted.setChecked(subNote.isCompleted());
            binding.checkSubCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> subNote.setCompleted(isChecked));
        }
    }
}
