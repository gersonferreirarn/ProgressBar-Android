package br.com.caicosoft.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbHorizontal, pbRedonda;
    Button btnCarregar;
    int progresso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbHorizontal = findViewById(R.id.pbHorizontal);
        pbRedonda = findViewById(R.id.pbRedonda);
        btnCarregar = findViewById(R.id.btnCarregar);

        pbRedonda.setVisibility(View.GONE);

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarProgressBar();
            }
        });
    }

    public void carregarProgressBar(){
        pbHorizontal.setProgress(progresso);

        pbRedonda.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //laço para iterar o progresso
                for(int i=0; i<=100; i++){

                    final int progresso = i;

                    // eh necessario uma thread dentro de outra para que atualize a interface
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pbHorizontal.setProgress(progresso);
                            if(progresso == 100){
                                pbRedonda.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

                    // a cada execução do for, ele vai esperar 100 milisegundos
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
