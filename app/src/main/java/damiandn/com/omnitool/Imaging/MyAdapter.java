package damiandn.com.omnitool.Imaging;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 1/1/2015.
 */
public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {             //pass in the ViewHolder to the Adapter

    private List<FluorescentProtein> fProteins;                //here is where we need an object to specify all the items
    private int rowLayout;
    private Context mContext;


    public MyAdapter(List<FluorescentProtein> fps, int rowLayout, Context context) {                //the constructor takes a list, a rowlayout Resource int ID, and a context

            this.fProteins = fps;                       //take all of the things passed into the constructer, and assign them to variables to be used below
            this.rowLayout = rowLayout;
            this.mContext = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);                      //This will take care of the logic for checking whether you need to create a new View or Recycle and old one. Before you had to do this manually
        return new ViewHolder(v);                   //return the above inflated view as a new ViewHolder                //we pass in the rowLayout int (which iterates through the list, I guess?)

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        FluorescentProtein protein = fProteins.get(position);               //grab the int position from the array of Fproteins


        if (protein.Em != 0 ) {
            viewHolder.fpName.setTextColor(Color.BLACK);

            viewHolder.fpName.setText(protein.name);
            viewHolder.llView.setBackgroundColor(Color.WHITE);
            viewHolder.fpEmission.setText(Integer.toString(protein.Em));
            viewHolder.fpExitation.setText(Integer.toString(protein.Ex));
        }else {
            viewHolder.fpName.setTextColor(Color.WHITE);
            viewHolder.fpName.setText(protein.name);
            //viewHolder.fpName.setBackgroundColor(protein.backColor);
            //viewHolder.fpEmission.setBackgroundColor(protein.backColor);
            //viewHolder.fpExitation.setBackgroundColor(protein.backColor);
            viewHolder.llView.setBackgroundColor(protein.backColor);
            viewHolder.fpExitation.setText("");
            viewHolder.fpEmission.setText("");

        }




    }

    @Override
    public int getItemCount() {
        return fProteins == null ? 0 : fProteins.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{                        //This is the viewholder which is used in the Adapter. It's baked into the SDK (hence extending a RecylerView.ViewHolder
        public TextView fpExitation;
        public TextView fpEmission;
        public TextView fpName;
        public LinearLayout llView;

        public ViewHolder(View itemView) {                                                  //pass in an itemview
            super(itemView);                                                                //call the superclass
            fpExitation = (TextView) itemView.findViewById(R.id.tvFPExcitation);
            fpEmission = (TextView) itemView.findViewById(R.id.tvFPEmission);
            fpName = (TextView) itemView.findViewById(R.id.tvFPName);
            llView = (LinearLayout) itemView.findViewById(R.id.llFPRecyclerCard);
        }
    }



    }








