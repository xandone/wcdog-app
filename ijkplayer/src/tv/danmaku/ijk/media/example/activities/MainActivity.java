package tv.danmaku.ijk.media.example.activities;

import tv.danmaku.ijk.media.example.application.AppActivity;
import tv.danmaku.ijk.media.player.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppActivity {

    private EditText url;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = (EditText) findViewById(R.id.url);
        play = (Button) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String t_url = url.getEditableText().toString();

                VideoActivity.intentTo(MainActivity.this, t_url.trim(), "Hello World");
            }
        });
    }
}
