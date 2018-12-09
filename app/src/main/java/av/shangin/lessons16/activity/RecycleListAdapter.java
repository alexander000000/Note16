package av.shangin.lessons16.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import av.shangin.lessons16.beans.NoteBean;
import av.shangin.lessons16.R;
import av.shangin.lessons16.utils.Param;

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.Holder2> {


    private ArrayList<NoteBean> mNoteList;//= new ArrayList<NoteBean>();
    //private final static int LIMIT = 256;
    //private final static int STEP = 64;

    public RecycleListAdapter() {
        mNoteList = new ArrayList<NoteBean>();
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

            NoteBean nb=null;
           // try {
                //String ss= mNoteList.get(i)
               nb = (NoteBean)mNoteList.get(i);
            //}
            //catch(Exception e){
            //    Log.d(Param.ERROR, "NoteBean nb = mNoteList.get(i); e=" + e.toString());
            //}
            //holder2.setCurrentSelectet(i);
            holder2.Bind(nb);

        }
    }

    @Override
    public int getItemCount() {
        return mNoteList!=null ? mNoteList.size():0;
    }


    public void AddItem(ArrayList<NoteBean> nl) {

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
        private NoteBean mNoteBean;


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
                    //MyIntentServiceOne.startActionLoadList(context,mNoteBean.getID());
                    //передаем параметр в Activity
                    mNewIntent.putExtra(Param.POS, mNoteBean.getID());

                    context.startActivity(mNewIntent);

                    //Log.d(Param.TAG, "setOnClickListener  ID="+mNoteBean.getID());

                }
            });
        }

        public void Bind (NoteBean noteBean){
            if (noteBean !=null) {
                mNoteBean = noteBean;
                //Log.d(Param.TAG, "Bind noteBean != null");
                mViewTitle.setText(mNoteBean.getHeader());
                mViewLastUpdate.setText(mNoteBean.getHumanDate());
            }
            //else Log.d(Param.TAG, "Bind noteBean is null");
        }
    }

}
