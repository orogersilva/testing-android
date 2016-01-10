package com.orogersilva.androidtesting.async;

import android.content.Context;
import android.os.AsyncTask;

import com.orogersilva.androidtesting.model.bll.UserBll;
import com.orogersilva.androidtesting.model.dal.UserDal;
import com.orogersilva.androidtesting.vo.User;

/**
 * Created by azevedor on 10/01/2016.
 */
public class AsyncSaveUser extends AsyncTask<Void, Void, Void> {

    // region FIELDS

    private Context mContext;
    private User mUser;
    private SaveUserCallback mCallback;

    // endregion

    // region CALLBACKS

    public interface SaveUserCallback {
        void onFinish();
    }

    // endregion

    // region CONSTRUCTORS

    public AsyncSaveUser(Context context, User user, SaveUserCallback callback) {
        mContext = context;
        mUser = user;
        mCallback = callback;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    protected Void doInBackground(Void... voids) {

        UserBll userBll = new UserBll(new UserDal(mContext));

        userBll.addUser(mUser);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        super.onPostExecute(result);

        mCallback.onFinish();
    }

    // endregion
}
