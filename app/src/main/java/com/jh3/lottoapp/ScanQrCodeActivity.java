package com.jh3.lottoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQrCodeActivity extends BaseActivity {

    private TextView tvRound, tvNumber, tvResult;
    private ArrayList<LottoWinningResult> lottoEntities;
    private int currentRound;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        initLayout();
        setDefaultSettings();

        scanQrCode();
    }

    private void scanQrCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(VerticalZxingActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d(LOG_TAG, result.getContents());

                final String qrUrl = result.getContents();
                new Thread() {
                    @Override
                    public void run() {
                        String id = qrUrl.substring(qrUrl.lastIndexOf("v=") + 2);
                        // redirect url
                        String finalQrUrl = "http://m.nlotto.co.kr/qr.do?method=winQr&v=" + id;

                        realm = Realm.getDefaultInstance();
                        RealmResults<LottoWinningResult> results =
                                realm.where(LottoWinningResult.class)
                                        .equalTo("id", id)
                                        .findAll();
                        if(results.size() == 0) {
                            getWebSite(finalQrUrl);
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(LOG_TAG, "이미 저장된 용지입니다");
                                    Toast.makeText(ScanQrCodeActivity.this, "이미 저장된 용지입니다", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });

                        }
                    }
                }.start();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getWebSite(final String urlString) {
        new Thread() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect(urlString).get();

                    // round
                    Element roundSpan = doc.select("span[class=f_orange]").first();
                    int round = Integer.parseInt(roundSpan.text());
                    currentRound= round;

                    // win result table
                    Element table = doc.select("TABLE[class = win_result fw_b mt10]").first();
                    Elements rows = table.select("tr");

                    for(Element tr : rows) {
                        Elements tds = tr.select("td");
                        int index = 0;
                        for (Element td : tds) {
                            if (td.children() == null) {
                                Log.d(LOG_TAG, "td no children");
                            } else {
                                if(index == 0) {
                                    builder.append(td.html()).append("\n");
                                    index += 1;
                                } else {
                                    String numbers = td.html();
                                    String[] tags = numbers.split(" ");
                                    for(int i = 0; i < tags.length; i += 2) {
                                        builder.append(tags[i] + " " + tags[i+1]).append("\n");
                                    }
                                }
                            }
                        }

                    }

                    String[] split = builder.toString().split("\n");
                    for(int i = 0; i < split.length; i += 7) {

                        int num1, num2, num3, num4, num5, num6;
                        String s1 = split[i+1];
                        String s2 = split[i+2];
                        String s3 = split[i+3];
                        String s4 = split[i+4];
                        String s5 = split[i+5];
                        String s6 = split[i+6];
                        if((s1.contains("span"))){
                            num1 = Integer.parseInt(s1.substring(s1.indexOf(">") + 1, s1.lastIndexOf("<")));
                        } else {
                            num1 = Integer.parseInt(s1.substring(s1.lastIndexOf("_") + 1, s1.lastIndexOf(".")));
                        }

                        if((s2.contains("span"))){
                            num2 = Integer.parseInt(s2.substring(s2.indexOf(">") + 1, s2.lastIndexOf("<")));
                        } else {
                            num2 = Integer.parseInt(s2.substring(s2.lastIndexOf("_") + 1, s2.lastIndexOf(".")));
                        }

                        if((s3.contains("span"))){
                            num3 = Integer.parseInt(s3.substring(s3.indexOf(">") + 1, s3.lastIndexOf("<")));
                        } else {
                            num3 = Integer.parseInt(s3.substring(s3.lastIndexOf("_") + 1, s3.lastIndexOf(".")));
                        }

                        if((s4.contains("span"))){
                            num4 = Integer.parseInt(s4.substring(s4.indexOf(">") + 1, s4.lastIndexOf("<")));
                        } else {
                            num4 = Integer.parseInt(s4.substring(s4.lastIndexOf("_") + 1, s4.lastIndexOf(".")));
                        }

                        if((s5.contains("span"))){
                            num5 = Integer.parseInt(s5.substring(s5.indexOf(">") + 1, s5.lastIndexOf("<")));
                        } else {
                            num5 = Integer.parseInt(s5.substring(s5.lastIndexOf("_") + 1, s5.lastIndexOf(".")));
                        }

                        if((s6.contains("span"))){
                            num6 = Integer.parseInt(s6.substring(s6.indexOf(">") + 1, s6.lastIndexOf("<")));
                        } else {
                            num6 = Integer.parseInt(s6.substring(s6.lastIndexOf("_") + 1, s6.lastIndexOf(".")));
                        }

                        String id = urlString.substring(urlString.lastIndexOf("v=") + 2);

                        final LottoWinningResult lottoWinningResult =
                                LottoWinningResult.builder()
                                .setId(id)
                                .setResult(split[i])
                                .setNum1(num1)
                                .setNum2(num2)
                                .setNum3(num3)
                                .setNum4(num4)
                                .setNum5(num5)
                                .setNum6(num6)
                                .setRound(round);
                        lottoEntities.add(lottoWinningResult);

                        // db save
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                LottoWinningResult result = realm.createObject(LottoWinningResult.class);
                                result.setId(lottoWinningResult.getId());
                                result.setRound(lottoWinningResult.getRound());
                                result.setResult(lottoWinningResult.getResult());
                                result.setNum1(lottoWinningResult.getNum1());
                                result.setNum2(lottoWinningResult.getNum2());
                                result.setNum3(lottoWinningResult.getNum3());
                                result.setNum4(lottoWinningResult.getNum4());
                                result.setNum5(lottoWinningResult.getNum5());
                                result.setNum6(lottoWinningResult.getNum6());
                                Log.d(LOG_TAG, "realm create object");
                            }
                        });
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 출력 및 회차 데이터 가져오기
                        showWinningNumber(currentRound);
                        showWinningResult();
                    }
                });
            }
        }.start();
    }

    private void logPrint(String result) {
        while (result.length() > 0) {
            if (result.length() > 4000) {
                result = result.substring(4000);
                Log.d(LOG_TAG, result);
            } else {
                Log.d(LOG_TAG, result);
                break;
            }
        }
    }

    private void showWinningNumber(int round) {
        tvRound.setText(round + "회");
        requestCurrentRoundResult(round);
    }

    private void showWinningResult() {
        String result = "";
        for(int i = 0; i < lottoEntities.size(); i++) {
            String row = i + "\t";
            row += lottoEntities.get(i).getResult() + "\t";
            row += lottoEntities.get(i).getNum1() + " ";
            row += lottoEntities.get(i).getNum2() + " ";
            row += lottoEntities.get(i).getNum3() + " ";
            row += lottoEntities.get(i).getNum4() + " ";
            row += lottoEntities.get(i).getNum5() + " ";
            row += lottoEntities.get(i).getNum6() + " ";
            result += row + "\n";
        }
        tvResult.setText(result);
    }

    private void requestCurrentRoundResult(int round) {
        LottoService service = NetworkManager.getInstance().getRetrofit(LottoService.class);
        Call<LottoWinningNumber> call = service.requestLottoNumber("getLottoNumber", round);
        call.enqueue(new Callback<LottoWinningNumber>() {
            @Override
            public void onResponse(Call<LottoWinningNumber> call, Response<LottoWinningNumber> response) {
                if(response.isSuccessful() && response.code() == 200) {
                    LottoWinningNumber winNumber = response.body();
                    printLottoWinningNumber(winNumber);
                } else {
                    Log.i(LOG_TAG, response.code() + "\t" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LottoWinningNumber> call, Throwable t) {
                Log.i(LOG_TAG, t.getMessage());
            }
        });
    }

    private void printLottoWinningNumber(LottoWinningNumber winNumber) {
        String result = "";
        result += "당첨일\t" + winNumber.getDrwNoDate() + "\n";
        result += "1등 당첨 인\t" + winNumber.getFirstPrzwnerCo() + "명\n";
        result += "1인당 당첨 금액\t" + winNumber.getFirstWinamnt() + "\n";
        result += "1등 당첨 번호\t";
        result += winNumber.getDrwtNo1() + " " +
                winNumber.getDrwtNo2() + " " +
                winNumber.getDrwtNo3() + " " +
                winNumber.getDrwtNo4() + " " +
                winNumber.getDrwtNo5() + " " +
                winNumber.getDrwtNo6() + " " +
                "보너스 " + winNumber.getBnusNo() + "\n";
        tvNumber.setText(result);
    }

    @Override
    protected void initLayout() {
        tvRound = (TextView) findViewById(R.id.tv_scan_qr_code_round);
        tvNumber = (TextView) findViewById(R.id.tv_scan_qr_code_winning_number);
        tvResult = (TextView) findViewById(R.id.tv_scan_qr_code_winning_result);
    }

    @Override
    protected void setDefaultSettings() {
        lottoEntities = new ArrayList<>();
    }
}
