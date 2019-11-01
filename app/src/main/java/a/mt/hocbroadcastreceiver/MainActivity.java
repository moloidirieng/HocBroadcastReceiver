package a.mt.hocbroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;
    BroadcastReceiver wifireceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

//            if(connectivityManager.isActiveNetworkMetered()==false){
//              chỗ nay được dùng như phía if dưới
//            }
            if(connectivityManager.getActiveNetworkInfo()!=null){

                Toast.makeText(context, "Bạn vừa mở internet", Toast.LENGTH_SHORT).show();
                btnDangNhap.setEnabled(true);
            }
            else {
                btnDangNhap.setEnabled(false);
                Toast.makeText(context, "Bạn vừa tắt internet", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifireceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wifireceiver!=null){
            unregisterReceiver(wifireceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btnDangNhap=findViewById(R.id.btnDangNhap);
    }
}
