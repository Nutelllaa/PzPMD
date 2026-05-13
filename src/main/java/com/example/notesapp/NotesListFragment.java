package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.notesapp.databinding.FragmentNotesListBinding;

public class NotesListFragment extends Fragment {
    private FragmentNotesListBinding binding;
    private NotesViewModel viewModel;
    private NotesListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotesListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new NotesListAdapter(viewModel.getNotes());
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.notesRecyclerView.setAdapter(adapter);
        binding.emptyText.setVisibility(viewModel.getNotes().isEmpty() ? View.VISIBLE : View.GONE);

        binding.btnAddNote.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_notesListFragment_to_createNoteFragment)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}
