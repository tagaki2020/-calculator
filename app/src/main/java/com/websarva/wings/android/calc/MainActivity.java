package com.websarva.wings.android.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    String oldTotal = "0";
    String tmpTotal = null;
    String total = null;
    String operator = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btKeyC = findViewById(R.id.keyClear);
        Button btKeyE = findViewById(R.id.keyEqual);
        Button btKeyP = findViewById(R.id.keyPlus);
        Button btKeyMi = findViewById(R.id.keyMinus);
        Button btKeyMu = findViewById(R.id.keyMultiply);
        Button btKeyD = findViewById(R.id.keyDivied);
        Button btKeyDot = findViewById(R.id.keyDot);
        Button btKey0 = findViewById(R.id.key0);
        Button btKey1 = findViewById(R.id.key1);
        Button btKey2 = findViewById(R.id.key2);
        Button btKey3 = findViewById(R.id.key3);
        Button btKey4 = findViewById(R.id.key4);
        Button btKey5 = findViewById(R.id.key5);
        Button btKey6 = findViewById(R.id.key6);
        Button btKey7 = findViewById(R.id.key7);
        Button btKey8 = findViewById(R.id.key8);
        Button btKey9 = findViewById(R.id.key9);

        ClickListenerCalc listenerCalc = new ClickListenerCalc();
        ClickListenerNum listenerNum = new ClickListenerNum();

        btKeyC.setOnClickListener(listenerCalc);
        btKeyE.setOnClickListener(listenerCalc);
        btKeyP.setOnClickListener(listenerCalc);
        btKeyMi.setOnClickListener(listenerCalc);
        btKeyMu.setOnClickListener(listenerCalc);
        btKeyD.setOnClickListener(listenerCalc);
        btKeyDot.setOnClickListener(listenerCalc);
        btKey0.setOnClickListener(listenerNum);
        btKey1.setOnClickListener(listenerNum);
        btKey2.setOnClickListener(listenerNum);
        btKey3.setOnClickListener(listenerNum);
        btKey4.setOnClickListener(listenerNum);
        btKey5.setOnClickListener(listenerNum);
        btKey6.setOnClickListener(listenerNum);
        btKey7.setOnClickListener(listenerNum);
        btKey8.setOnClickListener(listenerNum);
        btKey9.setOnClickListener(listenerNum);
    }

    /*
    数字以外を押したときの処理
     */
    private class ClickListenerCalc implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView output = findViewById(R.id.outPut);
            int id = view.getId();

            //表示文字列の末尾
            int totalCount = oldTotal.length();
            String totalCheck = oldTotal + ":";
            totalCheck = totalCheck.substring(totalCount - 1, totalCount);

            switch (id) {
                case R.id.keyClear:
                    total = "0";
                    output.setText(total);
                    oldTotal = total;
                    break;
                case R.id.keyEqual:
                    //表示文字列の末尾のチェック
                    if (!(totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals("."))) {

                        //＋、－、×、÷は全角
                        String[] nums = oldTotal.split("[＋,－,×,÷]", -1);
                        String[] strNums = oldTotal.split("(?<=[＋,－,×,÷])", -1);
                        int i = 0;

                        String strNum;

                        for (String num : nums) {
                            int strCount = num.length();
                            if (strCount != 0) {
                                strNums[i] += ":";
                                strNum = strNums[i].substring(strCount, strCount + 1);
                                Calc(operator, tmpTotal, num);

                                oldTotal = tmpTotal;
                                operator = strNum;
                                i++;
                            }
                        }
                        output.setText(oldTotal);

                        operator = null;
                        tmpTotal = null;
                    }
                    break;

                case R.id.keyDot:
                    //表示文字列のドット確認
                    String[] nums = oldTotal.split("[＋,－,×,÷]", -1);
                    int i = nums.length;
                    int count = dotCount(nums[i-1],".");

                    if (oldTotal.equals("0")) {
                        total = oldTotal + ".";
                        output.setText(total);
                        oldTotal = total;
                    }else if (totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals(".")) {
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    }else if(count >= 1){
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    } else {
                        total = oldTotal + ".";
                        output.setText(total);
                        oldTotal = total;
                    }
                    break;
                case R.id.keyPlus:
                    if (totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals(".")) {
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    } else {
                        total = oldTotal + "＋";
                        output.setText(total);
                        oldTotal = total;
                    }
                    break;
                case R.id.keyMinus:
                    if (totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals(".")) {
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    } else {
                        total = oldTotal + "－";
                        output.setText(total);
                        oldTotal = total;
                    }
                    break;
                case R.id.keyMultiply:
                    if (totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals(".")) {
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    } else {
                        total = oldTotal + "×";
                        output.setText(total);
                        oldTotal = total;
                    }
                    break;
                case R.id.keyDivied:
                    if (totalCheck.equals("＋") || totalCheck.equals("－") || totalCheck.equals("×") || totalCheck.equals("÷") || totalCheck.equals(".")) {
                        total = oldTotal;
                        output.setText(total);
                        oldTotal = total;
                    } else {
                        total = oldTotal + "÷";
                        output.setText(total);
                        oldTotal = total;
                    }
                    break;
            }
        }
    }

    /*
    「=」を押したときの、計算処理
     */
    public void Calc(String operator, String oldNum, String num) {
        BigDecimal calc = new BigDecimal("0");
        if (operator == null || operator.isEmpty()) {
            calc = new BigDecimal(num);
        } else {
            BigDecimal now = new BigDecimal(num);
            BigDecimal old = new BigDecimal(oldNum);

            switch (operator) {
                case "＋":
                    calc = old.add(now);
                    break;

                case "－":
                    calc = old.subtract(now);
                    break;

                case "×":
                    calc = old.multiply(now);
                    break;

                case "÷":
                    calc = old.divide(now,10, BigDecimal.ROUND_HALF_UP);
                    break;
            }
        }
        String calcStr = calc.toString();
        tmpTotal = calcStr;
    }

    /*
    表示文字列の「.」の数のチェック
     */
    public  int dotCount(String word,String key){
        int count = 0;
        int i=0;
        while( i + (key.length() - 1) < word.length()) {
            String str1 = word.substring(i, i + key.length() );
            if(str1.equals(key)) {
                count++;
            }
            i++;
        }
        return  count;
    }

    /*
    数字を押したときの表示
     */
    private class ClickListenerNum implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            TextView output = findViewById(R.id.outPut);
            int id = view.getId();

            if (oldTotal.equals("0")) {
                switch (id) {
                    case R.id.key0:
                        total = "0";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key1:
                        total = "1";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key2:
                        total = "2";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key3:
                        total = "3";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key4:
                        total = "4";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key5:
                        total = "5";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key6:
                        total = "6";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key7:
                        total = "7";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key8:
                        total = "8";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key9:
                        total = "9";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.keyDot:
                        total = ".";
                        output.setText(total);
                        oldTotal = total;
                        break;
                }
            } else {
                switch (id) {
                    case R.id.key0:
                        total = oldTotal + "0";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key1:
                        total = oldTotal + "1";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key2:
                        total = oldTotal + "2";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key3:
                        total = oldTotal + "3";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key4:
                        total = oldTotal + "4";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key5:
                        total = oldTotal + "5";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key6:
                        total = oldTotal + "6";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key7:
                        total = oldTotal + "7";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key8:
                        total = oldTotal + "8";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.key9:
                        total = oldTotal + "9";
                        output.setText(total);
                        oldTotal = total;
                        break;

                    case R.id.keyDot:
                        total = oldTotal + ".";
                        output.setText(total);
                        oldTotal = total;
                        break;
                }
            }


        }
    }
}
