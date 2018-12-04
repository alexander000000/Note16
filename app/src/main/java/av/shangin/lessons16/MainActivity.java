package av.shangin.lessons16;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //MainList newlistlayout

    public final static String POS = "POSITION";

    RecyclerView recyclerView;

    private Button mNewButton;
    private Button mSettingButton;
    private Intent mNewIntent;
    private Intent mSettingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistlayout);


        Intent mFromSave = getIntent();

        int position =-1;
        Uri myUri =mFromSave.getData();


        if(myUri==null)
        {
            position =mFromSave.getIntExtra(POS,-1);
        }

        if (position>0) {
            //показать в списке этот элемент
        }

        recyclerView = findViewById(R.id.NewMainList);
        recyclerView.setAdapter(new RecycleListAdapter());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));




        mNewButton = findViewById(R.id.buttonNew);
        mSettingButton = findViewById(R.id.buttonSettings);

        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to new Item activity
                mNewIntent = new Intent(MainActivity.this, ThirdActivity.class);

                //mNewIntent.putExtra(msfString,"MainActivity1");
                startActivity(mNewIntent);
            }
        });


        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to Setting activity
                mSettingIntent = new Intent(MainActivity.this, SecondActivity.class);

                //mNewIntent.putExtra(msfString,"MainActivity1");
                startActivity(mSettingIntent);
            }
        });
    }
}
