package com.example.notesapp;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesViewModel extends ViewModel {
    private final List<Note> notes = new ArrayList<>();

    public NotesViewModel() {
        notes.add(new Note(
                "Ідеї для проєкту",
                "Зібрати ідеї для майбутнього мобільного застосунку.",
                "https://httpbin.org/image/jpeg",
                "Київ: 50.4501, 30.5234",
                false,
                Arrays.asList(
                        new SubNote("Продумати головний екран", true),
                        new SubNote("Додати зображення", false),
                        new SubNote("Додати координати", false)
                )
        ));

        notes.add(new Note(
                "Місця для подорожі",
                "Список місць, які варто відвідати під час поїздки.",
                "https://httpbin.org/image/png",
                "Львів: 49.8397, 24.0297",
                false,
                Arrays.asList(
                        new SubNote("Знайти готель", true),
                        new SubNote("Перевірити маршрут", false)
                )
        ));
    }

    public List<Note> getNotes() { return notes; }

    public void addNote(Note note) { notes.add(0, note); }

    public Note findNote(String noteId) {
        for (Note note : notes) {
            if (note.getId().equals(noteId)) return note;
        }
        return null;
    }
}
