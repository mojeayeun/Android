package ca.cambrian.android.project.aaa.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    TextView caption,description;
    ImageView imageView ;
    Button buy_btn, back_btn ;
    String change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        caption = (TextView)findViewById(R.id.Captioooon);
        imageView = (ImageView)findViewById(R.id.imageView);
        description = (TextView)findViewById(R.id.description);
        buy_btn = (Button)findViewById(R.id.buy_btn);
        back_btn = (Button)findViewById(R.id.back_btn);


        Intent intent = getIntent();
        String val = intent.getStringExtra("param");

        Toast.makeText(this, "param: " + val, Toast.LENGTH_SHORT).show();

        if (val.equals("1")) {
            description.setText(getString(R.string.vancouver_description));
            caption.setText("ololol");
            imageView.setImageResource(R.drawable.vancouver);
        }else if (val.equals("2")) {
            description.setText(getString(R.string.toronto_desciption));
            caption.setText(getString(R.string.toronto));
            imageView.setImageResource(R.drawable.toronto);

        }else if (val.equals("3")) {
            description.setText(getString(R.string.vancouver_description));
            caption.setText(getString(R.string.vancouver));
            imageView.setImageResource(R.drawable.vancouver);
        }



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Detail.this, MainActivity2.class);
//                startActivity(i);

//                Intent i = new Intent(Detail.this, Purchase.class);
//                startActivity(i);
            }
        });
    }


}
