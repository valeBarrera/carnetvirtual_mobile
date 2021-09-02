package cl.chilllanubb.carnetvirtual.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cl.chilllanubb.carnetvirtual.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    private boolean userExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String currentToken = sharedPreferences.getString("token_telefono", null);

        userExists = !sharedPreferences.getString("user", "").equals("");

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TOKEN", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        // Log
                        String msg = String.format("El token es %s", token);
                        Log.d("TOKEN", msg);

                        if(currentToken == null || currentToken.isEmpty() || currentToken != token){
                            editor.putString("token_telefono", token);
                        }

                        editor.commit();
                    }
                });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userExists){
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}