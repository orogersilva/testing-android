package com.orogersilva.androidtesting.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.orogersilva.androidtesting.R;
import com.orogersilva.androidtesting.async.AsyncSendUser;
import com.orogersilva.androidtesting.net.UserNet;
import com.orogersilva.androidtesting.view.adapter.UserAdapter;
import com.orogersilva.androidtesting.vo.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // region FIELDS

    @Bind(R.id.users_recyclerView)
    RecyclerView mUsersRecyclerView;

    private RecyclerView.Adapter mUserAdapter;
    private RecyclerView.LayoutManager mUserLayoutManager;
    private List<User> mUsers;

    private UserNet mUserNet;

    final int USER_FORM_REQUEST = 1;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mUserNet = new UserNet(new Firebase("https://android-testing.firebaseio.com/users"));

        mUserLayoutManager = new LinearLayoutManager(this);
        mUsersRecyclerView.setLayoutManager(mUserLayoutManager);

        mUsers = new ArrayList<>();

        mUserAdapter = new UserAdapter(mUsers);
        mUsersRecyclerView.setAdapter(mUserAdapter);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == USER_FORM_REQUEST) {

            if (resultCode == RESULT_OK) {

                String userName = data.getStringExtra("name");
                String userAge = data.getStringExtra("age");
                String userCity = data.getStringExtra("city");

                User newUser = new User(userName, userAge, userCity);

                mUsers.add(newUser);

                mUserAdapter.notifyDataSetChanged();

                Toast.makeText(this, getString(
                        R.string.save_user_successful_message), Toast.LENGTH_LONG).show();
            }
        }
    }

    // endregion

    // region OTHER METHODS

    @OnClick(R.id.add_user_button)
    public void addUser() {

        startActivityForResult(new Intent(this, FormActivity.class), USER_FORM_REQUEST);
    }

    @OnClick(R.id.send_user_button)
    public void sendUsers() {

        AsyncSendUser asyncSendUser = new AsyncSendUser(mUserNet, mUsers, new AsyncSendUser.SendUserCallback() {

            @Override
            public void onFinish() {

                mUsers.clear();
                mUserAdapter.notifyDataSetChanged();

                Toast.makeText(getBaseContext(), "Usuários enviados.", Toast.LENGTH_LONG).show();
            }
        });

        asyncSendUser.execute();
    }

    // endregion
}
