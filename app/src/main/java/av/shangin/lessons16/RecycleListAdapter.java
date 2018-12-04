package av.shangin.lessons16;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.Holder> {


    private List<String>  colorList = new ArrayList<>();
    private final static int LIMIT = 256;
    private final static int STEP = 64;

    public RecycleListAdapter() {
        InitColor();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //viewGroup - это как раз и есть список
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return new Holder(inflater.inflate(R.layout.item_of_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.Bind(colorList.get(i));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    private String Component (int value){
        String result = Integer.toHexString(value);
        return result.length()==2 ? result : "0"+result;
    }

    private void InitColor(){
        for (int red=0; red<LIMIT; red+=STEP){
            for (int green=0; green<LIMIT; green+=STEP){
                for (int blue=0; blue<LIMIT; blue+=STEP){
                    String color ="#"+Component(red)+Component(green)+Component(blue);
                    colorList.add(color);
                }

            }

        }

    }

// - класс holder для RecycleListAdapter для recyclerView
    public static class  Holder extends RecyclerView.ViewHolder{

        // это элементы с view элемента списка
        private TextView ColorName;
        private View preview;
        private boolean isClick=false;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ColorName = itemView.findViewById(R.id.textVeiwInItem);
            preview = itemView.findViewById(R.id.viewInItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isClick==false) {
                        v.setBackgroundColor(Color.CYAN);
                        isClick=true;
                    }
                    else{
                        v.setBackgroundColor(Color.WHITE);
                        isClick=false;
                    }

                }
            });
        }

        public void Bind (String color1){
            ColorName.setText(color1);
            preview.setBackgroundColor(Color.parseColor(color1));
        }
    }

}
