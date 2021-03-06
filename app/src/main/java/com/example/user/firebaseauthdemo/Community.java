package com.example.user.firebaseauthdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by HYB on 2017. 10 . 11..
 */

public class Community extends Fragment
{

    private RecyclerView mBlogList;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        View view = inflater.inflate(R.layout.community, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        lm.setReverseLayout(true);
        lm.setStackFromEnd(true);

        mBlogList = (RecyclerView) view.findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(lm);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                ( Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase)
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position)
            {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
                viewHolder.setNickname(model.getNickname());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title)
        {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            if (title.length() > 30)
            {
                post_title.setText(title.substring(0, 30));
            }
            else
            {
                post_title.setText(title);
            }
        }

        public void setDesc(String desc)
        {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            if (desc.length() > 50)
            {
                post_desc.setText(desc.substring(0, 50));
            }
            else
            {
                post_desc.setText(desc);
            }
        }

        public void setImage(Context ctx, String image)
        {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }
        public void setNickname(String nickname)
        {
            TextView nick_title = (TextView)mView.findViewById(R.id.nick_Title);
            nick_title.setText(nickname);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.community_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == R.id.action_add)
        {
            Intent intent = new Intent(getActivity(), PostActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
