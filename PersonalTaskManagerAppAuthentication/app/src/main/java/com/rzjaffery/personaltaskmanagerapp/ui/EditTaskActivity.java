package com.rzjaffery.personaltaskmanagerapp.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.rzjaffery.personaltaskmanagerapp.R;
import com.rzjaffery.personaltaskmanagerapp.model.Task;
import com.rzjaffery.personaltaskmanagerapp.viewmodel.TaskViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription, editDate;
    private Spinner spinnerPriority;
    private TaskViewModel taskViewModel;
    private int taskId = -1;

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d",
                            selectedDay, selectedMonth + 1, selectedYear);
                    editDate.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        editDate = findViewById(R.id.edit_date);
        spinnerPriority = findViewById(R.id.spinner_priority);
        Button saveButton = findViewById(R.id.button_save);

        editDate.setOnClickListener(v -> showDatePickerDialog());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        if (getIntent().hasExtra("task_id")) {
            taskId = getIntent().getIntExtra("task_id", -1);
            editTitle.setText(getIntent().getStringExtra("title"));
            editTitle.setEnabled(false); // disable editing the title

            editDescription.setText(getIntent().getStringExtra("description"));
            editDate.setText(getIntent().getStringExtra("date"));

            String priority = getIntent().getStringExtra("priority");
            spinnerPriority.setSelection(adapter.getPosition(priority));
        }


        saveButton.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            String description = editDescription.getText().toString();
            String dateString = editDate.getText().toString();
            String priority = spinnerPriority.getSelectedItem().toString();

            if (description.isEmpty() || dateString.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Date date;
            try {
                date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateString);
            } catch (ParseException e) {
                Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
                return;
            }

            Task updatedTask = new Task(title, description, date, priority, false);
            updatedTask.setId(taskId);
            taskViewModel.update(updatedTask);

            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
