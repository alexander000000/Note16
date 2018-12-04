package av.shangin.lessons16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    //Setting  setting_layout
    private Button mButtonОК;
    private Intent mMainList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        mButtonОК = findViewById(R.id.buttonОК);

        mButtonОК.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(SecondActivity.this, MainActivity.class);
                int pos =saveSetting();

                //mMainList.putExtra(MainActivity.POS,pos);

                startActivity(mMainList);
            }
        });

    }

    private int saveSetting(){
        //TO-DO code for save in DB
        return 0;
    }
}
