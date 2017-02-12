package com.angelo.databasetutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    databasehelper myDB;

    EditText fname,lname,marks,id;
    Button btn_add,btn_viewdata,btn_update,btn_delete,btn_nextpage;
    ListView lsttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new databasehelper(this);

        fname = (EditText)findViewById(R.id.tv_fname);
        lname = (EditText)findViewById(R.id.tv_lname);
        marks = (EditText)findViewById(R.id.tv_remarks);
        id = (EditText)findViewById(R.id.tv_id);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_viewdata= (Button)findViewById(R.id.btn_viewdata);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_nextpage = (Button)findViewById(R.id.btn_nextpage);




        add_data();
        viewall();
        update_data();
        delete_data();


        btn_nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this,secondactivity.class));
            }
        });
    }

    public void add_data(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =   myDB.insertData(fname.getText().toString(),
                                                       lname.getText().toString(),
                                                       marks.getText().toString());

                if (isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void update_data(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated = myDB.updateData(id.getText().toString(),
                        fname.getText().toString(),
                        lname.getText().toString(),
                        marks.getText().toString());

                if (isUpdated == true)

                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();


            }
        });
    }

    public void delete_data(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDB.deleteData(id.getText().toString());
                if (deletedRows > 0)

                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

            }
        });
    }



    public void viewall(){
        btn_viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor show_data =  myDB.get_all_data();


                if (show_data.getCount() ==0){

                    showMessage("Error","No Data Found");
                        return;

                }

                StringBuffer buffer = new StringBuffer();
                while (show_data.moveToNext()){
                    buffer.append("ID: " +show_data.getString(0)+"\n");
                    buffer.append("First Name: " +show_data.getString(1)+"\n");
                    buffer.append("Last Name: " +show_data.getString(2)+"\n");
                    buffer.append("Remarks: " +show_data.getString(3)+"\n\n");
                }

               showMessage("Data",buffer.toString());

            }
        });
    }







    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }




}
