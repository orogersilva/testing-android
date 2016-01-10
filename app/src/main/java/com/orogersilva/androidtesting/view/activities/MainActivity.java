package com.orogersilva.androidtesting.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.orogersilva.androidtesting.R;
import com.orogersilva.androidtesting.view.adapter.UserAdapter;
import com.orogersilva.androidtesting.vo.User;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // region FIELDS

    @Bind(R.id.users_recyclerView)
    RecyclerView mUsersRecyclerView;

    private RecyclerView.Adapter mUserAdapter;
    private RecyclerView.LayoutManager mUserLayoutManager;

    @Bind(R.id.add_button)
    Button mAddButton;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mUserLayoutManager = new LinearLayoutManager(this);
        mUsersRecyclerView.setLayoutManager(mUserLayoutManager);

        List<User> users  = Arrays.asList(
                new User("Roger", "27", "Alvorada"),
                new User("Bianca", "34", "Porto Alegre"),
                new User("Jéssica", "22", "São Paulo"),
                new User("Otávio", "55", "Rio de Janeiro"),
                new User("João", "19", "Belo Horizonte"),
                new User("Iara", "44", "Curitiba"),
                new User("Vanda", "43", "Florianópolis"),
                new User("Monique", "26", "Salvador"),
                new User("Diego", "21", "Fortaleza"),
                new User("Bruno", "25", "Belém"),
                new User("Carlos", "23", "Niterói"),
                new User("Mariana", "25", "Gramado"),
                new User("Sandra", "45", "Bauru"),
                new User("Lauro", "31", "Natal"),
                new User("Neiva", "29", "Goiania")
        );

        mUserAdapter = new UserAdapter(users);
        mUsersRecyclerView.setAdapter(mUserAdapter);
    }

    // endregion
}
