package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notesapp.databinding.ItemNoteBinding;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {
    private final List<Note> notes;

    public NotesListAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
    }

    @Override
    public int getItemCount() { return notes.size(); }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final ItemNoteBinding binding;

        public NoteViewHolder(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setNote(Note note) {
            binding.tvNoteTitle.setText(note.getTitle());
            binding.tvNoteDescription.setText(note.getDescription());
            binding.tvSubNotesCount.setText(note.countCompletedSubNotes() + "/" + note.getSubNotes().size() + " пунктів");
            binding.checkCompleted.setOnCheckedChangeListener(null);
            binding.checkCompleted.setChecked(note.isCompleted());
            binding.checkCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> note.setCompleted(isChecked));

            binding.noteCard.setOnClickListener(v -> openDetails(note));
            binding.btnDetails.setOnClickListener(v -> openDetails(note));
        }

        private void openDetails(Note note) {
            Bundle args = new Bundle();
            args.putString("noteId", note.getId());
            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_notesListFragment_to_noteDetailsFragment, args);
        }
    }
}
