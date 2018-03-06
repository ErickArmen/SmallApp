package com.eoma.pruebabp.main;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eoma.pruebabp.BaseActivity;
import com.eoma.pruebabp.R;
import com.eoma.pruebabp.RespuestaPOJO;
import com.eoma.pruebabp.login.Login;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;


public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory vmFactory;
    private MainViewModel viewModel;

    @BindView(R.id.suma)          EditText suma;
    @BindView(R.id.name)          TextView name;
    @BindView(R.id.edad)          TextView edad;
    @BindView(R.id.hobbies)       TextView hobbies;
    @BindView(R.id.buttonUsuario) Button buttonUsuario;
    @BindView(R.id.receiver)      EditText receiver;
    @BindView(R.id.progress_bar_usu)ProgressBar progressBarUsu;
    @BindView(R.id.progress_bar_email)ProgressBar progressBarEmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        super.initViews();

        viewModel = ViewModelProviders.of(this, vmFactory).get(MainViewModel.class);

        viewModel.checkLogin().observe(this, new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {
                if(s == null){
                    goLogin();
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                int lenght = charSequence.length();

                if(lenght > 0 && lenght > start) {

                    if (Character.getNumericValue(charSequence.charAt(start)) == 0) {

                        suma.setText("");
                        viewModel.goCompute(String.valueOf(charSequence))
                                .observe(MainActivity.this, new Observer<Integer>() {

                            @Override
                            public void onChanged(@Nullable Integer integer) {
                                showAlert(integer);
                            }
                        });
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
        suma.addTextChangedListener(textWatcher);

        TextView.OnEditorActionListener doneListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE){
                    clickEmail();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
                return false;
            }
        };
        receiver.setOnEditorActionListener(doneListener);
    }

    private void showAlert(Integer integer){

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(getString(R.string.total_suma, integer));
        dlgAlert.setTitle("Sum");
        dlgAlert.create();
        dlgAlert.show();

    }

    @OnClick(R.id.buttonUsuario)
    void clickUsuario(){

        isLoading(true);
        viewModel.giveMeUser().observe(this, new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {

                if (strings != null) {
                    name.setText(getString(R.string.nameUser, strings.get(0), strings.get(1), strings.get(2)));
                    edad.setText(getString(R.string.edadUser, strings.get(3)));
                    hobbies.setText(getString(R.string.hobbiesUser, strings.get(4), strings.get(5), strings.get(6),
                            strings.get(7)));
                } else {
                    name.setText(R.string.error);
                }

                isLoading(false);
            }
        });
    }

    private void clickEmail(){

        String mReceiver = receiver.getText().toString();

        if (validEmail(mReceiver)) {

            isLoading(true);
            viewModel.sendEmail(mReceiver).observe(this, new Observer<RespuestaPOJO>() {

                @Override
                public void onChanged(@Nullable RespuestaPOJO resp) {

                    if (resp != null) {
                        receiver.setText("");
                        Toast.makeText(MainActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    isLoading(false);
                }
            });
        }else{
            Toast.makeText(this, R.string.invalidateEmails, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void isLoading(Boolean state){

        if (state){
            buttonUsuario.setClickable(false);
            buttonUsuario.setVisibility(View.GONE);
            receiver.setClickable(false);
            receiver.setVisibility(View.GONE);
            progressBarUsu.setVisibility(View.VISIBLE);
            progressBarEmail.setVisibility(View.VISIBLE);
        }else{
            progressBarUsu.setVisibility(View.GONE);
            progressBarEmail.setVisibility(View.GONE);
            buttonUsuario.setClickable(true);
            buttonUsuario.setVisibility(View.VISIBLE);
            receiver.setClickable(true);
            receiver.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.CerrarSesion:
                CerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void goLogin(){
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void CerrarSesion(){
        viewModel.logOut();
        goLogin();
    }
}
