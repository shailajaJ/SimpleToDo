package com.codepath.simpletodo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText editText;
    Button saveButton;
    String item = "";
    int position = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        editText = (EditText) findViewById(R.id.textView);
        saveButton = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        item = intent.getStringExtra("item");
        position = intent.getIntExtra("position", 0);

        editText.setText(item);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()) {
                    alertMessage();
                } else {
                    String stringToPassBack = editText.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("item", stringToPassBack);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void alertMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditItemActivity.this);
        builder.setTitle("Warning");
        builder.setMessage("Please enter text before adding to the list view");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
