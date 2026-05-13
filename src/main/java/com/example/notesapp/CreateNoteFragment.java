package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.notesapp.databinding.FragmentCreateNoteBinding;
import java.util.ArrayList;
import java.util.List;

public class CreateNoteFragment extends Fragment {
    private FragmentCreateNoteBinding binding;
    private NotesViewModel viewModel;
    private final List<SubNote> tempSubNotes = new ArrayList<>();
    private SubNotesListAdapter subNotesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        subNotesAdapter = new SubNotesListAdapter(tempSubNotes);
        binding.subNotesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.subNotesRecyclerView.setAdapter(subNotesAdapter);

        binding.btnAddSubNote.setOnClickListener(v -> {
            String title = binding.inputSubNote.getText().toString().trim();
            if (!title.isEmpty()) {
                tempSubNotes.add(new SubNote(title, false));
                subNotesAdapter.notifyItemInserted(tempSubNotes.size() - 1);
                binding.inputSubNote.setText("");
            }
        });

        binding.btnCreate.setOnClickListener(v -> {
            String title = binding.inputTitle.getText().toString().trim();
            String description = binding.inputDescription.getText().toString().trim();
            String imageUrl = binding.inputImageUrl.getText().toString().trim();
            String location = binding.inputLocation.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(requireContext(), "Введіть назву нотатки", Toast.LENGTH_SHORT).show();
                return;
            }

            if (description.isEmpty()) description = "Опис відсутній";
            if (imageUrl.isEmpty()) imageUrl = "https://httpbin.org/image/jpeg";
            if (location.isEmpty()) location = "Координати не вказані";

            viewModel.addNote(new Note(title, description, imageUrl, location, false, new ArrayList<>(tempSubNotes)));
            requireActivity().onBackPressed();
        });
    }
}
