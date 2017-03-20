package com.antonioleiva.mvpexample.app.Login;

import android.os.Handler;
import android.text.TextUtils;

import com.antonioleiva.mvpexample.app.Login.interfaces.LoginInteractor;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener onLoginFinishedListener) {
         new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    onLoginFinishedListener.onUsernameError();
                    error = true;
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    onLoginFinishedListener.onPasswordError();
                    error = true;
                    return;
                }
                if (!error){
                    onLoginFinishedListener.onSuccess();
                }
            }
        }, 2000);
    }
}
