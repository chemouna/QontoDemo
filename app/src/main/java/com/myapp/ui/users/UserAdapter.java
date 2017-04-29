package com.myapp.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.R;
import com.myapp.model.User;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
    implements Consumer<List<User>> {

    private List<User> users = new ArrayList<>();

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void accept(List<User> users) throws Exception {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    User getUser(int position) {
        return users.get(position);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView nameTv;

        UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(User user) {
            nameTv.setText(user.getName());
        }
    }
}
