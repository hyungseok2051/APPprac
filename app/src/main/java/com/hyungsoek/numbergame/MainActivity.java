package com.hyungsoek.numbergame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //객체선언선언 및 변수 선언
    EditText edtNumber;
    Button btnGameStart, btnConfirm ,btnMenuAuto , btnFinish;
    TextView tvHint;
    ImageView imgGame;
    int comNumber; // 난수 발생 숫자 변수
    int myNumber; //에디트텍스트에 입력한 숫자를 담을 변수
    int count;//게임 진행 횟수
    String[] menu={"커피", "떡볶이","순대","어묵","짜장면","짬뽕"};
    Random rand=new Random();
    int choice; //난수 발생 값 저장
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("숫자맞추기게임");
        //선언된 객체와 xml 연결
        edtNumber=findViewById(R.id.edtNumber);
        btnGameStart=findViewById(R.id.btnGameStart);
        btnConfirm=findViewById(R.id.btnConfirm);
        btnMenuAuto=findViewById(R.id.btnMenuAuto);
        btnFinish=findViewById(R.id.btnFinish);
        tvHint=findViewById(R.id.tvHint);
        imgGame=findViewById(R.id.imgGame);

        tvHint.setText("게임시작버튼을 누르셔야 게임이 시작됩니다");
        //메서드로 기능처리
        //게임시작버튼(1부터 100사이의 난수를 발생)
        btnGameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 comNumber=(int)(Math.random()*100)+1;
                 count=0;
                 btnConfirm.setEnabled(true);
                 btnGameStart.setEnabled(false);
                 tvHint.setText("자!! 게임이 시작되었습니다");
                 imgGame.setImageResource(R.drawable.number);
                 btnMenuAuto.setVisibility(View.INVISIBLE);
            }
        });
        //확인버튼(에디트텍스트에 숫자를 입력한 후 결과 확인)
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                myNumber=Integer.parseInt(edtNumber.getText().toString());
                if(myNumber > comNumber){
                    tvHint.setText("당신의 숫자가 너무 커요 좀더 작은 수를 넣어 보세요(시도횟수="+count+")");
                    imgGame.setImageResource(R.drawable.wrong);
                }else if(myNumber<comNumber){
                    tvHint.setText("당신의 숫자가 너무 작아요 좀더 큰 수를 넣어보세요(시도횟수="+count+")");
                    imgGame.setImageResource(R.drawable.wrong);
                }else{
                    tvHint.setText("축하하합니다 맞추셨습니다. (시도횟수="+count+")");
                    imgGame.setImageResource(R.drawable.good);
                    btnConfirm.setEnabled(false);
                    btnGameStart.setEnabled(true);
                    btnMenuAuto.setVisibility(View.VISIBLE);
                }//if종료
                edtNumber.setText("");
            }
        });
        //복불복 내기 메뉴 자동선택 버튼
        btnMenuAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            choice=rand.nextInt( menu.length);
            tvHint.setText("당첨!! 복불복 내기 메뉴는 "+menu[choice]+"입니다");
            }
        });
        //앱 종료 버튼
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}