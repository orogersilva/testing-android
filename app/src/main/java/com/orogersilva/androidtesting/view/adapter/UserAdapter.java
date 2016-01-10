package com.orogersilva.androidtesting.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orogersilva.androidtesting.R;
import com.orogersilva.androidtesting.vo.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by azevedor on 10/01/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    // region FIELDS

    private List<User> mUsers;

    // endregion

    // region CONSTRUCTORS

    public UserAdapter(List<User> user) {
        mUsers = user;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_itemview, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = mUsers.get(position);

        holder.mNameTextView.setText(user.getName());
        holder.mAgeTextView.setText(user.getAge());
        holder.mCityTextView.setText(user.getCity());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    // endregion

    // region HOLDERS

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // region FIELDS

        @Bind(R.id.name_textView)
        TextView mNameTextView;

        @Bind(R.id.age_textView)
        TextView mAgeTextView;

        @Bind(R.id.city_textView)
        TextView mCityTextView;

        // endregion

        // region CONSTRUCTORS

        public ViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        // endregion
    }

    // endregion
}
