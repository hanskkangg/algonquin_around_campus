package algonquin.cst2335.algonquin_around_campus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<String> titles,addresses,contactNumbers,storeHours,imageURL;

    public Adapter(Context context, List<String> titles,List<String> addresses, List<String> contactNumbers, List<String> storeHours, List<String> imageURL){

        this.inflater = LayoutInflater.from(context);
        this.titles = titles;
        this.addresses = addresses;
        this.contactNumbers = contactNumbers;
        this.storeHours = storeHours;
        this.imageURL = imageURL;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titles.get(position);
        String adr = addresses.get(position);
        String contact = contactNumbers.get(position);
        String storeHrs=  storeHours.get(position);
        String img = imageURL.get(position);

        holder.title.setText(title);
        holder.content2.setText(adr);
        holder.content3.setText(contact);
        holder.content4.setText(storeHrs);

        //using picasso to download image
        Picasso.get().load(img).into(holder.listImg);

    }

    @Override
    public int getItemCount() {
        return titles.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView listImg;
        TextView title,content;
        TextView InfoAddresses,content2;
        TextView InfoContactNumbers,content3;
        TextView InfoStoreHours,content4;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            listImg = itemView.findViewById(R.id.InfoImage);
            title = itemView.findViewById(R.id.InfoTitle);
            InfoAddresses = itemView.findViewById(R.id.InfoAddress);
            InfoContactNumbers = itemView.findViewById(R.id.InfoContact);
            InfoStoreHours = itemView.findViewById(R.id.InfoHours);
        }

    }
}
