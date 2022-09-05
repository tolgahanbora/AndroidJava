package com.dataflair.tictactoe.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dataflair.tictactoe.R;

import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;
    TextView mTextView;

    int OYUNCU_PC = 0;
    int PLAYER_Human = 1;
    int activePlayer = OYUNCU_PC;
    boolean isGameActive = true;
    //x ve o nun yerleşeceği kutuların pozisyonlarını sıraladım
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        //Butonlara event oluşturdum
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

        //Onclick ile Butonlara event oluşturdum
        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);


        //Onclick restart eventi ile oyunu yeniden başlattım.
        restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });


        compterPlayer();
    }

    private void compterPlayer() {

        //X ve O harflerinin yerleşmesi için random sayılar ürettim ve bunları tanımladım
        int randomNumber = new Random().nextInt(filledPositions.length);

        //Diziden değer aldım
        int buttonToClick = filledPositions[randomNumber];
        //Butonun 2 kere tıklanmamasını kontrol ettim ve oyuncuların sırası değiştikçe X ve O ya renk atadım
        if (buttonToClick == -1) {
            if (randomNumber == 0) {
                mButton0.setText("O");
                filledPositions[randomNumber] = OYUNCU_PC;          //Butonu tanımladım ve kazananı kontrol etmek için dizide
                                                               //diğer değerleri güncellemek için bu butonu oluşturdum
                                                               //böylelikle kazananı belirlemiş olacaktım
                mButton0.setBackgroundColor(Color.RED);
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 1) {
                mButton1.setText("O");
                mButton1.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 2) {
                mButton2.setText("O");
                mButton2.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 3) {
                mButton3.setText("O");
                mButton3.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 4) {
                mButton4.setText("O");
                mButton4.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 5) {
                mButton5.setText("O");
                mButton5.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 6) {
                mButton6.setText("O");
                mButton6.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 7) {
                mButton7.setText("O");
                mButton7.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
            if (randomNumber == 8) {
                mButton8.setText("O");
                mButton8.setBackgroundColor(Color.RED);
                filledPositions[randomNumber] = OYUNCU_PC;
                activePlayer = PLAYER_Human;
                mTextView.setText("İnsan oyuncunun Sırası");
            }
        } else {
            compterPlayer();    //Oluşturulan pc oyuncusu methodunu çağırdım.

        }

        //Kazananı kontrol ettim
        checkWinner();

        //Beraberliği kontrol ettim
        checkDraw();

    }

    @Override
    public void onClick(View view) {

        //Oyunun aktifliğini kontrol ettim ve return ile bunu döndürdüm
        if (!isGameActive)
            return;

        //buton id'sini çağırdım
        Button clickedBtn = findViewById(view.getId());

        //buton özniteliğini(tag) çağırdım
        int clickedTag = Integer.parseInt(view.getTag().toString());

        //Butona tıklandığında tekrar butona tıklarsa bir sonuç gelmemesini sağladım
        if (filledPositions[clickedTag] != -1) {
            //Do nothing
            return;

        }
        //Dizide ki değeri oyuncunun kazandığı değer ile güncelledim,
        filledPositions[clickedTag] = activePlayer;

        //Eğer oynama sırası PC de ise X renginin değişmesini sağladım
        if (activePlayer == OYUNCU_PC) {
            compterPlayer();
        } else {
            clickedBtn.setText("x");
            clickedBtn.setBackgroundColor(Color.GREEN);
            mTextView.setText("Bilgisayarın Sırası");

            //Bilgisayarın sırası olduğu için onu çağırdım
            compterPlayer();
        }

        //Kazananı kontrol ettim
        checkWinner();

        //Beraberliği kontrol ettirdim
        checkDraw();
    }


    private void checkDraw() {
        int count = 0;

        //Yarattığım dizideki for döngüsüyle kontrol ettim ve i ye eşit değilse tek tek arttırdım
        for (int i = 0; i < 9; i++) {
            if (filledPositions[i] != -1) {
                count++;
            }
        }

        //ihtimalleri dizi de ki değerler ile eşit değilse bunu sayaç ile arttırdım, oyun berabere bitecek şekilde
        // şart koşuluna atadım
        if (count == 9) {
            //Ekrana yazdırmadan önce oyunun kazananı olup olmadığını kontrol ettim
            if (checkWinner())
                return;
            showWinner("Oyun Berabere");
            isGameActive = false;   //oyunu durdurdum
        }
    }

    private boolean checkWinner() {

        //Bütün kazanma ihtimallerini 3 ün 9 lu kombinasyonunu aldım daha sonra tek tek onları dizi olarak atadım
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        //Bütün kazanabilen pozisyonları value değişkenlerime atadım
        for (int i = 0; i < 8; i++) {
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            //Değerin eşit olup olmadığını kontrol ettim
            if (filledPositions[value0] != -1) {
                if (filledPositions[value0] == filledPositions[value1] && filledPositions[value1] == filledPositions[value2]) {



                    //Oyunun aktifliğini kontrol ettim
                    isGameActive = false;

                    //Kazananı Belirlemek
                    if (filledPositions[value0] == OYUNCU_PC) {
                        showWinner("Oyuncu 0 Kazandı");

                    } else {
                        showWinner("Oyuncu X Kazandı");
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

        //Oyunu yeniden başlatmak için tekrar aynı classları çağırdım, bunu yapmak içni intent kullandım
        Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
        startActivity(intent);
        finish();
    }

}