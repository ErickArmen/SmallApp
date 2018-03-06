package com.eoma.pruebabp.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eoma.pruebabp.BaseActivity;
import com.eoma.pruebabp.R;
import com.eoma.pruebabp.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;



public class Login extends BaseActivity {

    @BindView(R.id.login)       Button login;
    @BindView(R.id.textUser)    TextInputEditText userInp;
    @BindView(R.id.textPass)    TextInputEditText passInp;
    @BindView(R.id.progress_bar)ProgressBar progressBar;
    @BindView(R.id.txtprueba)   TextView txtprueba;
    @Inject                     ViewModelProvider.Factory vmFactory;
    private                     LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        super.initViews();

        viewModel = ViewModelProviders.of(this, vmFactory).get(LoginViewModel.class);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_anim);
        txtprueba.startAnimation(animation);
    }

    @OnClick(R.id.login)
    void login(){

        final String user = userInp.getText().toString();
        String pass = passInp.getText().toString();

        if (!user.isEmpty() && !pass.isEmpty()) {

            loginButtonState(false);

            viewModel.tryLogin(user, pass).observe(this, new Observer<Integer>() {

                @Override
                public void onChanged(@Nullable Integer integer) {

                    if (integer != null) {
                        switch (integer) {
                            case 0:
                                Toast.makeText(Login.this, getString(R.string.welcome, user), Toast.LENGTH_LONG).show();
                                goMain();
                                break;
                            case 1:
                                showErrorAndButton(R.string.unknown_user);
                                break;
                            case 2:
                                showErrorAndButton(R.string.unknown_pass);
                                break;
                            case 3:
                                showErrorAndButton(R.string.required_fields);
                                break;
                            case 4:
                                showErrorAndButton(R.string.error);
                                break;
                            default:
                                showErrorAndButton(R.string.unknown_user);
                                break;
                        }
                    } else {
                        loginButtonState(true);
                    }
                }
            });
        } else {
            Toast.makeText(Login.this, R.string.required_fields, Toast.LENGTH_LONG).show();
        }
    }

    private void showErrorAndButton(int error){
        loginButtonState(true);
        Toast.makeText(Login.this, error, Toast.LENGTH_LONG).show();
    }

    private void loginButtonState(Boolean state){

        if (state){
            progressBar.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            login.setClickable(true);
        }else{
            login.setVisibility(View.GONE);
            login.setClickable(false);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
