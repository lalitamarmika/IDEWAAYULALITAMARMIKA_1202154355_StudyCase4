package android.lalita.com.idewaayulalitamarmika_1202154355_studycase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CariGambar extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    EditText namaurl;
    ImageView gmbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        //mengakses id edit text dan image view yang ada pada layout
        namaurl = findViewById(R.id.editUrl);
        gmbr = findViewById(R.id.gambar);
    }

    //method yang akan dieksekusi ketika button klik untuk mencari gambar di klik
    public void cari(View view) {
        //picasso memanggil kelas cari gambar dan meload gambar yang didapatkan dari value edit text
        Picasso.with(CariGambar.this).load(namaurl.getText().toString())
                //menampilkan gambar sebelum dimuat
                .placeholder(R.mipmap.ic_launcher_round)
                //apabila picasso gagal fetching remote asset
                .error(R.mipmap.ic_launcher)
                //menargetkan variable object imageView
                .into(gmbr);
    }
}
