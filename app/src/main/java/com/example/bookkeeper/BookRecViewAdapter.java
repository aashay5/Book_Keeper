package com.example.bookkeeper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.bookkeeper.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<Book> books=new ArrayList<>();
    private Context miContext;
    private String parentActvity;

    public BookRecViewAdapter(Context miContext, String parentActvity) {
        this.miContext = miContext;
        this.parentActvity = parentActvity;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookRecViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtName.setText(books.get(position).getName());
        Glide.with(miContext).asBitmap().load(books.get(position).getImageUrl()).into(holder.imgBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(miContext,BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                miContext.startActivity(intent);
            }
        });
        holder.author.setText(books.get(position).getAuthor());
        holder.shortDes.setText(books.get(position).getShortDes());
        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.VISIBLE);
            holder.expand.setVisibility(View.GONE);

            if(parentActvity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }else if(parentActvity.equals("alreadyRead")){

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(miContext);
                        builder.setMessage("Are you sure you want to delete "+books.get(position).getName()+" ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFromAlreadyRead(books.get(position))){
                                    Toast.makeText(miContext, "removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });
            }else if(parentActvity.equals("wantToRead")){
                AlertDialog.Builder builder=new AlertDialog.Builder(miContext);
                builder.setMessage("Are you sure you want to delete "+books.get(position).getName()+" ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Utils.getInstance().removeFromWantToRead(books.get(position))){
                            Toast.makeText(miContext, "removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }else if(parentActvity.equals("currentlyReading")){
                AlertDialog.Builder builder=new AlertDialog.Builder(miContext);
                builder.setMessage("Are you sure you want to delete "+books.get(position).getName()+" ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Utils.getInstance().removeFromCurrentlyReading(books.get(position))){
                            Toast.makeText(miContext, "removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }else{
                AlertDialog.Builder builder=new AlertDialog.Builder(miContext);
                builder.setMessage("Are you sure you want to delete "+books.get(position).getName()+" ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Utils.getInstance().removeFromFavouriteBooks(books.get(position))){
                            Toast.makeText(miContext, "removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        }else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.GONE);
            holder.expand.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {

        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    private final CardView parent;
    private final TextView btnDelete;
    private final ImageView imgBook;
    private final TextView txtName;
    private final ImageView expand;
        private final RelativeLayout expandedLayout;
    private final TextView author;
        private final TextView shortDes;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView.findViewById(R.id.parent);
        imgBook = itemView.findViewById(R.id.imgBook);
        txtName = itemView.findViewById(R.id.txtName);
        author = itemView.findViewById(R.id.author);
        shortDes = itemView.findViewById(R.id.shortdes);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        expandedLayout = itemView.findViewById(R.id.expandedLayout);
        ImageView collapse = itemView.findViewById(R.id.collapseBtn);
        expand = itemView.findViewById(R.id.expandBtn);
        expand.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            }
        });
        collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            }
        });
    }

}
}
