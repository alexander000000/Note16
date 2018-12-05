package av.shangin.lessons16;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    //New item new_item_layout

    private Button mButtonSave;
    private Intent mMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item_layout);

    mButtonSave = findViewById(R.id.buttonSave);

    mButtonSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mMainList = new Intent(ThirdActivity.this, MainActivity.class);
            int pos =saveItem();

            mMainList.putExtra(Param.POS,pos);

            startActivity(mMainList);
        }
    });

    }

    private int saveItem(){
        //TO-DO code for save in DB
        return 0;
    }


}
