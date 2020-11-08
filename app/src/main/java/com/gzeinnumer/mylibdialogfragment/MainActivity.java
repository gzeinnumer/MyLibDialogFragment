package com.gzeinnumer.mylibdialogfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog();
            }
        });
    }

    private void callDialog() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment previous = getSupportFragmentManager().findFragmentByTag(CustomMyLibDialog.TAG);
        if(previous != null){
            transaction.remove(previous);
        }
        CustomMyLibDialog dialog = CustomMyLibDialog.newInstance();
        dialog.show(transaction, CustomMyLibDialog.TAG);
    }
}