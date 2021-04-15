package ua.kpi.spscs.theintentfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // take the data and the extras of the intent
        TextView txt = findViewById(R.id.txt);
        Uri url = getIntent().getData();
        if (url != null) {
            txt.setText(url.toString());
        }
        Bundle extras = getIntent().getExtras();
        System.out.println(url);
        System.out.println(extras);
    }

    public void triggerCallIntent(View view) {
        final Random random = new Random();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + String.format(Locale.getDefault(), "555-55-%d", random.nextInt(99))));
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }
        else
        {
            startActivity(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        TextView txt = findViewById(R.id.txt);
        txt.setText("NewIntent!");
    }
}