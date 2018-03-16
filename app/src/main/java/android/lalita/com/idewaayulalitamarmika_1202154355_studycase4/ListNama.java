package android.lalita.com.idewaayulalitamarmika_1202154355_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {
//deklarasi variable
private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        //mengakses id listview pada layout
        lv = findViewById(R.id.listnama);
    }

    public void mulai(View view) {
        new getListNama(lv).execute();
    }

    //menetapkan tipe data yang akan digunakan pada parameter, dan yang tidak digunakan menggunakan void
    class getListNama extends AsyncTask<String, Void, String> {
        //mendeklarasikan variable yang akan digunakan
        ListView lv;
        ArrayAdapter aa;
        ArrayList <String> listnama;
        ProgressDialog pd;
        //konstruktor
        public getListNama(ListView lv) {
            this.lv = lv;
            //membuat progress dialog baru
            pd = new ProgressDialog(ListNama.this);
            //membuat array baru untuk menampung nilai dari listview
            listnama = new ArrayList<>();
        }

        //dipanggil di thread sebelum tugas dieksekusi, digunakan untuk mempersiapkan tugas
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //set progress style menjadi spinner, circular spinning progress bar
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //set title dari progress menjadi Loading data
            pd.setTitle("Loading Data");
            //mengeset cancelable progressdialog menjadi true
            pd.setCancelable(true);
            //apabila button cancel process di klik
            pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //maka progress dialog akan ditutup
                    pd.dismiss();
                    //tidak menampilkan listnama
                    getListNama.this.cancel(true);
                }
            });
            //merubah mode ideterminate mode menjadi true untuk proses dialog ini
            pd.setIndeterminate(true);
            //max range dari progress
            pd.setMax(100);
            //set progress menjadi 0
            pd.setProgress(0);
            //membuat dan menampilkan progress dialog
            pd.show();
        }


        //dipanggil pada thread latar belakang setelah onpreexecute
        //menjalankan komputasi latar belakang, mengembalikan hasil, dan meneruskan hasilnya ke onpostexecute
        @Override
        protected String doInBackground(String... strings) {
            //membuat adapter  baru dengan nama aa
            aa = new ArrayAdapter<>(ListNama.this, android.R.layout.simple_list_item_1,listnama);
            //resource yang mengambil referensi menggunakan value yang disediakan dengan nama atribut
            final String [] namamhsw = getResources().getStringArray(R.array.namamhsw);
            //perulangan yang terjadi sebanyak jumlah nama pada list namamhsw
            for(int i=0; i<namamhsw.length;i++){
                //variable percentage yang menampung besar persentase untuk pernama yang di load
                final long percentage = 100L*i/namamhsw.length;
                try{
                    //membuat runable baru
                    Runnable runnable = new Runnable(){
                        @Override
                        public void run() {
                            //text yang ditampilkan pada dialog
                            pd.setMessage((int) percentage+"%");
                        }
                    };
                    //menjalankan runnable pada UI Thread
                    runOnUiThread(runnable);
                    //menunda thread selama 0.25 sekon
                    Thread.sleep(250);
                    //menambah nama-nama mahasiswa yang ada pada array namamhsw kedalam arraylist listnama
                    listnama.add(namamhsw[i]);
                } catch (InterruptedException e) {
                    //auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method yang berjalan di thread UI setelah komputasi latar belakang selesai
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //mengatur adapter untuk listview
            lv.setAdapter(aa);
            //menutup progress dialog
            pd.dismiss();
        }
    }
}
