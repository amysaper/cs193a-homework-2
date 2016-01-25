//This simple to-do list app works by letting a user add items and then clicking the "Add" button.
// Long press to delete.

package com.example.amysaper.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    //making private variables to be re-used
    private ArrayList<String> todo;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //set the to-do list to store 5 dynamic items
        ListView todoList = (ListView) findViewById(R.id.todo_list);
        todoList.setOnItemLongClickListener(this);

        todo = new ArrayList<>();
        todo.add("do laundry");
        todo.add("pick up dry cleaning");
        todo.add("make this Android app");
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                todo

        );
        todoList.setAdapter(adapter);
    }

    //Save contents of list when instance is changed, eg screen rotated
    @Override
    protected void onSaveInstanceState (Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("todo", todo);
    }

    //restore list when rotated or otherwise changed
    @Override
    protected void  onRestoreInstanceState (Bundle bundle){
        super.onRestoreInstanceState(bundle);
        todo = bundle.getStringArrayList("todo");
    }

    //deletes items with a long click
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {
        ListView todolist = (ListView) findViewById(R.id.todo_list);
        String toDelete = todolist.getItemAtPosition(index).toString();
        //delete the item you long click
        todo.remove(toDelete);
        //update with the deleted item
        adapter.notifyDataSetChanged();
        return true;
    }

    //adds the item to the list when you click add
    public void addItemToList(View view) {
        EditText myEditText = (EditText) findViewById(R.id.add_item);
        String text = myEditText.getText().toString();
        todo.add(0,text);
        adapter.notifyDataSetChanged();
    }
}
