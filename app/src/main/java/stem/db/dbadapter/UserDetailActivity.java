package stem.db.dbadapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import stem.db.R;
import stem.db.arrayadapter.ListItemAdapter;
import stem.db.model.UserModel;

public class UserDetailActivity extends AppCompatActivity {

    private Context context;
    private ListView listView;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        init();
    }

    private void init() {
        context = this;
        listView = (ListView) findViewById(R.id.listView);

        dbAdapter = new DBAdapter(context);
        dbAdapter.open();


        setListValues();

    }

    private void setListValues() {
        Cursor cursor = dbAdapter.getAllUser();


        int count = cursor.getCount();
        cursor.moveToFirst();

        if (count > 0) {
            ArrayList<UserModel> alUsers = new ArrayList<>();

            for (int i = 0; i < count; i++) {

                String email_id = cursor.getString(1);
                String password = cursor.getString(2);


                alUsers.add(new UserModel(email_id, password));

                cursor.moveToNext();
            }

            ListItemAdapter adapter = new ListItemAdapter(context, R.layout.list_items, alUsers);
            // create a new ListView, set the adapter and item click listener
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(UserDetailActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
        }
    }

}
