package com.guohua.day44_qrcode;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.client.android.Intents;

public class MainActivity extends AppCompatActivity {

    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll= (LinearLayout) findViewById(R.id.ll);
      t= (TextView) findViewById(R.id.tv);

        Button button=new Button(this);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        button.setLayoutParams(params);
        ll.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Intents.Scan.ACTION);

                int w = getResources().getDisplayMetrics().widthPixels;
                int h = getResources().getDisplayMetrics().heightPixels;
                int size = h < w ? h : w;
                size = size >> 1;

                intent3.putExtra(Intents.Scan.WIDTH, size);
                intent3.putExtra(Intents.Scan.HEIGHT, size);

                /**
                 * 设置扫描二维码的模式
                 */
                intent3.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
                startActivityForResult(intent3, 0x003);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x003&&resultCode==RESULT_OK){
           String url= data.getStringExtra(Intents.Scan.RESULT);
            t.setText(data.getStringExtra(Intents.Scan.RESULT));
           Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
