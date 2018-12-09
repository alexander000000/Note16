package av.shangin.lessons16;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.Holder2> {


    private ArrayList<NoteBin> mNoteList;//= new ArrayList<NoteBin>();
    //private final static int LIMIT = 256;
    //private final static int STEP = 64;

    public RecycleListAdapter() {
        mNoteList = new ArrayList<NoteBin>();
        //Log.d(Param.TAG, "RecycleListAdapter 25");

        //LoadList(nl);
    }

    @NonNull
    @Override
    public Holder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Log.d(Param.TAG, "onCreateViewHolder i=" + i);
        //viewGroup - это как раз и есть список
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());


        return new Holder2(inflater.inflate(R.layout.item_of_list,viewGroup,false));
        //return new Holder2(inflater.inflate(R.layout.item_of_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder2 holder2, int i) {

        //Log.d(Param.TAG, "onBindViewHolder i=" + i);

        if(mNoteList!=null && i<mNoteList.size() && i>=0) {

            NoteBin nb=null;
           // try {
                //String ss= mNoteList.get(i)
               nb = (NoteBin)mNoteList.get(i);
            //}
            //catch(Exception e){
            //    Log.d(Param.ERROR, "NoteBin nb = mNoteList.get(i); e=" + e.toString());
            //}
            //holder2.setCurrentSelectet(i);
            holder2.Bind(nb);

        }
    }

    @Override
    public int getItemCount() {
        return mNoteList!=null ? mNoteList.size():0;
    }


    public void AddItem(ArrayList<NoteBin> nl) {

        if(mNoteList!=null) {
            if(nl!=null) mNoteList.addAll(nl);
            //Log.d(Param.TAG, "AddItem nl.size()=" + nl.size());
        }
        else {

            if(nl!=null) mNoteList=nl;
            //Log.d(Param.TAG, "AddItem nl is null");

        }
        //Log.d(Param.TAG, "AddItem mNoteList.size()=" + mNoteList.size());
    }

// - класс holder для RecycleListAdapter для recyclerView
    public static class Holder2 extends RecyclerView.ViewHolder{

        // это элементы с view элемента списка
        private TextView mViewTitle;
        private TextView mViewLastUpdate;
        //private View preview;
        private View mItemLayout;
        private boolean isClick=false;
        private int currentSelectet =-1;
        private NoteBin mNoteBin;


        public int getCurrentSelectet(){
            return currentSelectet;
        }
        public void setCurrentSelectet(int i){
            currentSelectet=i;
        }


        public Holder2(@NonNull View itemView) {
            super(itemView);
            mViewTitle = itemView.findViewById(R.id.textVeiwTitle);
            mViewLastUpdate = itemView.findViewById(R.id.textVeiwDate);
            mItemLayout = itemView.findViewById(R.id.itemOfList2);


            //if (mItemLayout!=null) mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context =v.getContext();
                    Intent mNewIntent = new Intent(context, EditActivity.class);

                    //Запуск сервиса получения одного элемента
                    //TODO: Надо понять почему не работает!
                    //тут не работает! Ресивер не ловится!
                    //MyIntentServiceOne.startActionLoadList(context,mNoteBin.getID());
                    //передаем параметр в Activity
                    mNewIntent.putExtra(Param.POS,mNoteBin.getID());

                    context.startActivity(mNewIntent);

                    //Log.d(Param.TAG, "setOnClickListener  ID="+mNoteBin.getID());

                }
            });
        }

        public void Bind (NoteBin noteBin){
            if (noteBin!=null) {
                mNoteBin =noteBin;
                //Log.d(Param.TAG, "Bind noteBin != null");
                mViewTitle.setText(mNoteBin.getHeader());
                mViewLastUpdate.setText(mNoteBin.getHumanDate());
            }
            //else Log.d(Param.TAG, "Bind noteBin is null");
        }
    }

}
