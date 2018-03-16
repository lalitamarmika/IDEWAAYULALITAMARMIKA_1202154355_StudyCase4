package android.lalita.com.idewaayulalitamarmika_1202154355_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    //method yang akan dieksekusi ketika button AsyncTask di klik
    public void buttonNama(View view) {
        //membuat intent baru dan berpindah dari menu utama ke kelas list nama
        Intent intent = new Intent(this,ListNama.class);
        //memulai intent
        startActivity(intent);
    }

    //method yang akan dieksekusi ketika button Cari Gambar di klik
    public void buttonGambar(View view) {
        //membuat intent baru dan berpindah dari menu utama ke kelas Cari Gambar
        Intent intent = new Intent(this, CariGambar.class);
        //memulai intent
        startActivity(intent);
    }
}
