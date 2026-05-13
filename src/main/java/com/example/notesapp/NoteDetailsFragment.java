package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.notesapp.databinding.FragmentNoteDetailsBinding;

public class NoteDetailsFragment extends Fragment {
    private FragmentNoteDetailsBinding binding;
    private NotesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNoteDetailsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String noteId = getArguments() != null ? getArguments().getString("noteId") : null;
        Note note = noteId == null ? null : viewModel.findNote(noteId);

        if (note == null) {
            binding.tvTitle.setText("Нотатку не знайдено");
            binding.tvDescription.setText("");
            return;
        }

        binding.tvTitle.setText(note.getTitle());
        binding.tvDescription.setText(note.getDescription());
        binding.tvLocation.setText("📍 " + note.getLocation());

        Glide.with(this)
                .load(note.getImageUrl())
                .placeholder(R.drawable.image_placeholder)
                .into(binding.imageNote);

        binding.subNotesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.subNotesRecyclerView.setAdapter(new SubNotesListAdapter(note.getSubNotes()));
    }
}
