package ru.dachkovska.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initThemeChooser();

        button=(Button)findViewById(R.id.buttonAccept);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    // Инициализация радиокнопок
    private void initThemeChooser() {

        initRadioButton(findViewById(R.id.radioButtonMaterialDark), AppThemeDarkCodeStyle);
        initRadioButton(findViewById(R.id.radioButtonMaterialLight), AppThemeLightCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(AppThemeLightCodeStyle))).setChecked(true);
    }

    // Все инициализации кнопок очень похожи, поэтому создадим метод для переиспользования
    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // сохраним настройки
                setAppTheme(codeStyle);
                // пересоздадим активити, чтобы тема применилась
                recreate();
            }
        });
    }
}