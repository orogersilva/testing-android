package com.orogersilva.androidtesting.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.orogersilva.androidtesting.R;
import com.orogersilva.androidtesting.async.AsyncSaveUser;
import com.orogersilva.androidtesting.model.bll.UserBll;
import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.vo.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by azevedor on 10/01/2016.
 */
public class FormActivity extends AppCompatActivity {

    // region FIELDS

    @Bind(R.id.user_name_editText)
    EditText mUserNameEditText;
    @Bind(R.id.user_age_editText)
    EditText mUserAgeEditText;
    @Bind(R.id.user_city_editText)
    EditText mUserCityEditText;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ButterKnife.bind(this);

        setTitle(getString(R.string.add_friend));
    }

    // endregion

    // region OTHER METHODS

    @OnClick(R.id.save_user_button)
    public void onClickSave() {

        String name = mUserNameEditText.getText().toString();
        String age = mUserAgeEditText.getText().toString();
        String city = mUserCityEditText.getText().toString();

        final User newUser = new User(name, age, city);

        AsyncSaveUser asyncSaveUser = new AsyncSaveUser(this, newUser, new AsyncSaveUser.SaveUserCallback() {

            @Override
            public void onFinish() {

                Intent newUserIntent = new Intent();

                newUserIntent.putExtra("name", newUser.getName());
                newUserIntent.putExtra("age", newUser.getAge());
                newUserIntent.putExtra("city", newUser.getCity());

                setResult(RESULT_OK, newUserIntent);
                finish();
            }
        });

        asyncSaveUser.execute();
    }

    // endregion


}
