package tubesrpl.washco.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tubesrpl.washco.DetailPostActivity;
import tubesrpl.washco.MainActivity;
import tubesrpl.washco.Model.Post;
import tubesrpl.washco.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    FirebaseAuth mAuth;

    Context context;
    List<Post> postList;

    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mLaundryName;
        public TextView mTitlePost;
        public TextView mPrice;
        public TextView mAddress;
        public ImageView mImagePost;
        public ImageView favoriteIm;
        public TextView favNum;
        public CardView cardViewPost;

        public ViewHolder(View itemView) {
            super(itemView);

            mLaundryName = itemView.findViewById(R.id.tv_laundry_name);
            mTitlePost = itemView.findViewById(R.id.tv_title_post);
            mPrice = itemView.findViewById(R.id.tv_price);
            mAddress = itemView.findViewById(R.id.tv_address);
            mImagePost = itemView.findViewById(R.id.img_post);
            favoriteIm = itemView.findViewById(R.id.favorite);
            favNum = itemView.findViewById(R.id.fav_num);
            cardViewPost = itemView.findViewById(R.id.cardViewPost);
        }
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post, parent, false);
        mAuth = FirebaseAuth.getInstance();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Post post = postList.get(position);

        holder.mLaundryName.setText(post.getLaundryname());
        holder.favNum.setText(String.valueOf(post.getFavCount()));
        Glide.with(context).load(post.getImagePost()).into(holder.mImagePost);

//        holder.mTitlePost.setText(post.getTitlePost());
        holder.mAddress.setText(post.getAddress());
        holder.mPrice.setText(post.getPrice());

//        String a = post.getId();
//        DatabaseReference likepost = FirebaseDatabase.getInstance().getReference(MainActivity.table1);
//        String currentUser = mAuth.getCurrentUser().getUid();
//
//        likepost.child(a).child("like").setValue(currentUser);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Post");
        final DatabaseReference mostafa = ref.child("favCount");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseFood = FirebaseDatabase.getInstance().getReference(MainActivity.table1).child(mAuth.getUid()).child("favCount");

        holder.favoriteIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.favoriteIm.getDrawable().getConstantState() ==
                        context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp).getConstantState()) {
                    int recent = post.getFavCount();

                    addLike(post.getId(), ++recent, holder);
                    Toast.makeText(context, "Sudah Terlike", Toast.LENGTH_SHORT).show();
                } else {
                    // Unlike
                    deleteLike();
                    holder.favoriteIm.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });
        /*
        holder.favoriteIm.setOnClickListener(new View.OnClickListener() {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Post");
            DatabaseReference mostafa = ref.child(post.getId()).child("favCount");
            @Override
            public void onClick(View v) {
                if (holder.favoriteIm.getDrawable().getConstantState() ==
                        context.getResources().getDrawable( R.drawable.ic_favorite_border_black_24dp).getConstantState()) {
                    holder.favoriteIm.setImageResource(R.drawable.ic_favorite_black_24dp);

                    mostafa.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int count = (int) dataSnapshot.child("favCount").getValue();
                            //int count = dataSnapshot.child(post.getId()).child("favCount").getValue(Integer.class)
                            mostafa.setValue(count++);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            throw databaseError.toException();
                        }

                    });

                }
                else holder.favoriteIm.setImageResource(R.drawable.ic_favorite_border_black_24dp);

                mostafa.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = (int) dataSnapshot.child("favCount").getValue();
//                                count = (long) dataSnapshot.child("favCount").getValue();
                        //int count = dataSnapshot.child(post.getId()).child("favCount").getValue(Integer.class);
                        mostafa.setValue(count--);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        throw databaseError.toException();
                    }

                });
            }
        });
        */

        holder.cardViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPostActivity.class);
                intent.putExtra("id", post.getId());
                intent.putExtra("Laundryname", post.getLaundryname());
                intent.putExtra("Images", post.getImagePost());
                intent.putExtra("Title", post.getTitlePost());
                intent.putExtra("Address", post.getAddress());
                intent.putExtra("Price", post.getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addLike(String id, int favCount, ViewHolder holder) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Post");
        String key = databaseReference.child("favCount").getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(id + "/" + key, favCount);
        databaseReference.updateChildren(childUpdates);
        holder.favoriteIm.setImageResource(R.drawable.ic_favorite_black_24dp);
    }

    public void deleteLike() {

    }

}

