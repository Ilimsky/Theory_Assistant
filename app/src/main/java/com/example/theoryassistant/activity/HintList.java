package com.example.theoryassistant.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.theoryassistant.R;

import java.util.ArrayList;

public class HintList extends AddHint {

    GridView listView;
    ArrayList<Hint> list;
    HintListAdapter adapter = null;
    Cursor cursor;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dashboard);
        listView = findViewById(R.id.ListView);
        list = new ArrayList<>();
        activity = this;

        adapter = new HintListAdapter(this, R.layout.hint_items, activity, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HintList.this, HintClicked.class);
                startActivity(intent);
            }
        });
        cursor = AddHint.sqLitePhoneDetails.getData("SELECT * FROM PHONE");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String answer = cursor.getString(2);
            list.add(new Hint(id, question, answer));
        }

        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(HintList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = AddHint.sqLitePhoneDetails.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(HintList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = AddHint.sqLitePhoneDetails.getData("SELECT id FROM PHONE");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(final int idPhone) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(HintList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AddHint.sqLitePhoneDetails.deleteData(idPhone);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList() {
        // get all data from sqlite
        Cursor cursor = AddHint.sqLitePhoneDetails.getData("SELECT * FROM PHONE");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String answer = cursor.getString(2);
            list.add(new Hint(id, question, answer));
        }
        adapter.notifyDataSetChanged();
    }

    private void showDialogUpdate(Activity activity, final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_hint_activity);
        dialog.setTitle("Update");

        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtdetais = (EditText) dialog.findViewById(R.id.editdetails);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddHint.sqLitePhoneDetails.updateData(
                            edtName.getText().toString().trim(),
                            edtdetais.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }
}