package com.dataflair.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;
    TextView mTextView;

    int OYUNCU_O = 0;
    int OYUNCU_X = 1;
    int activePlayer = OYUNCU_O;
    boolean isGameActive = true;
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Çok f
        mTextView = (TextView) findViewById(R.id.UserTurnTxt);
        mButton0 = (Button) findViewById(R.id.Button0);
        mButton1 = (Button) findViewById(R.id.Button1);
        mButton2 = (Button) findViewById(R.id.Button2);
        mButton3 = (Button) findViewById(R.id.Button3);
        mButton4 = (Button) findViewById(R.id.Button4);
        mButton5 = (Button) findViewById(R.id.Button5);
        mButton6 = (Button) findViewById(R.id.Button6);
        mButton7 = (Button) findViewById(R.id.Button7);
        mButton8 = (Button) findViewById(R.id.Button8);
        restartGameBtn = (Button) findViewById(R.id.RestartGameBtn);

        //Çok fazla onclick kullandığım, buton kullandığım için this metodunu kullandım
        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);


        //Restart butonuna event oluşturdum
        restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    //Butona basıldığında geri çağırılmasını sağladım
    @Override
    public void onClick(View view) {

        //Oyunun aktifliğini kontrol edip bunu return ile döndürdüm
        if (!isGameActive)
            return;

        //butonun id sini get ile çağırdım
        Button clickedBtn = findViewById(view.getId());

        //buton özniteliğini(tag) çağırdım
        int clickedTag = Integer.parseInt(view.getTag().toString());

        //Butona tıklandığında tekrar butona tıklarsa bir sonuç gelmemesini sağladım
        if (filledPositions[clickedTag] != -1) {
            //Bir sonuç verilmemesini sağladım ve bunu döndürdüm
            return;

        }
        //Dizide ki değeri kullanıcının puanı olarak güncelledim
        filledPositions[clickedTag] = activePlayer;

        //Eğer activeplayer oyuncu olursa butona oyuncu yazacağız ve renk değişecek, daha sonra ki oyuncuya X olarak atayacağım
        if (activePlayer == OYUNCU_O) {
            clickedBtn.setText("o");
            clickedBtn.setBackgroundColor(Color.RED);
            activePlayer = OYUNCU_X;
            mTextView.setText("Oyuncu X-Sırası");
        } else {
            clickedBtn.setText("x");
            activePlayer = OYUNCU_O;
            clickedBtn.setBackgroundColor(Color.GREEN);
            mTextView.setText("Oyuncu O-SIRASI");
        }

        //kazananı kontrol ettim
        checkWinner();

        //beraberliği kontrol ettim
        checkDraw();
    }

    private void checkDraw() {
        int count = 0;

        //dizi de ki tüm sayıları for döngüsüyle kontrol ettim ve i ye eşit değilse tek tek arttırdım
        for (int i = 0; i < 9; i++) {
            if (filledPositions[i] != -1) {
                count++;
            }
        }
        //yarattığım dizi de ki tüm değerlere sayaç eşit değilse oyun berabere biter
        if (count == 9) {
            //Uyarı ekranını ekrana yazdırmadan önce kimin kazandığını kontrol ettirdim
            if (checkWinner())
                return;
            showWinner("Oyun Berabere");
            isGameActive = false;  //Oyunu durdurdum, çünkü oyun berabere bitti tekrar butona basması gerek
        }
    }

    private boolean checkWinner() {

        //Bütün kazanma ihtimallerini 3 ün 9 lu kombinasyonunu aldım daha sonra tek tek onları dizi olarak atadım
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        //Tüm kazanan pozisyonları for döngüsüyle arttırdım
        for (int i = 0; i < 8; i++) {
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            //Bütün kazanabilen pozisyonları value değişkenlerime atadım
            if (filledPositions[value0] != -1) {
                if (filledPositions[value0] == filledPositions[value1] && filledPositions[value1] == filledPositions[value2]) {

                    //Oyunun aktifliğini kontrol ettim
                    isGameActive = false;

                    //Kazananı ekrana yazdırdım
                    if (filledPositions[value0] == OYUNCU_O) {
                        showWinner("Kazanan Oyuncu O");

                    } else {
                        showWinner("Kazanan Oyuncu X");
                    }
                    //Sonucu booleana atadığım true değeri ile döndürdüm, bu şekilde kazanana göre sonuç yazılacak
                    return true;
                }


            }
        }
        return false;
    }

    private void showWinner(String winner) {

        //Oyunu tekrar başlatmak için yada kazananı göstermek için uyarı mesajı oluşturdum
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Tekrar Oyna", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();
    }

    private void restartGame() {

        //Varsayılan oyuncuyu insana eşitledim
        activePlayer = OYUNCU_O;

        //Değerleri varsayılana tanımladım
        filledPositions = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        //bütün butonalrda ki yazıları boş bıraktım (set ettim)
        mButton0.setText("");
        mButton1.setText("");
        mButton2.setText("");
        mButton3.setText("");
        mButton4.setText("");
        mButton5.setText("");
        mButton6.setText("");
        mButton7.setText("");
        mButton8.setText("");

        //X ve 0 lara renk tanımladım
        mButton0.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton1.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton2.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton3.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton4.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton5.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton6.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton7.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton8.setBackgroundColor(Color.parseColor("#BFBFBF"));

        //İnsan olan oyuncunun sırasını tanımladım ve ekrana yazdırdım
        mTextView.setText("Oyuncu 0 sırası");

        //oyunun aktifliğini kontrol ettim
        isGameActive = true;
    }
}