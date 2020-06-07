package duksung.android.hororok.ugeubi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Tab extends FragmentActivity {

    TabLayout tabs;

    // 각 페이지에 대한 프레그먼트
    Alarm_fragment alarm_fragment;
    Medicine_kit_fragment medicine_kit_fragment;
    Main_fragment main_fragment;
    Search_fragment search_fragment;
    Setting_fragment setting_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        alarm_fragment = new Alarm_fragment();
        medicine_kit_fragment = new Medicine_kit_fragment();
        main_fragment = new Main_fragment();
        search_fragment = new Search_fragment();
        setting_fragment = new Setting_fragment();


        // 시작했을 때 보여줄 fragment 화면 설정
        getSupportFragmentManager().beginTransaction().add(R.id.container, main_fragment).commit();

        tabs = findViewById(R.id.tabs);

        // 메인 아이콘만 클릭 표시
        final TabLayout.Tab alarm_btn = tabs.newTab().setIcon(R.drawable.reminder);
        final TabLayout.Tab medicine_kit_btn = tabs.newTab().setIcon(R.drawable.medicine_kit_icon);
        final TabLayout.Tab main_btn = tabs.newTab().setIcon(R.drawable.main_icon_clicked);
        final TabLayout.Tab search_btn = tabs.newTab().setIcon(R.drawable.search_icon);
        final TabLayout.Tab settings_btn = tabs.newTab().setIcon(R.drawable.settings);

        // 탭 레이아웃에 각각의 탭 버튼 추가
        tabs.addTab(alarm_btn);
        tabs.addTab(medicine_kit_btn);
        tabs.addTab(main_btn);
        tabs.addTab(search_btn);
        tabs.addTab(settings_btn);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;

                // 알림 페이지로 이동
                if(position == 0){
                    selected = alarm_fragment;
                    alarm_btn.setIcon(R.drawable.reminder_icon_clicked);
                    medicine_kit_btn.setIcon(R.drawable.medicine_kit_icon);
                    main_btn.setIcon(R.drawable.main_icon);
                    search_btn.setIcon(R.drawable.search_icon);
                    settings_btn.setIcon(R.drawable.settings);
                }


                // 우리집 구급상자 페이지로 이동
                else if(position == 1){
                    selected = medicine_kit_fragment;
                    alarm_btn.setIcon(R.drawable.reminder);
                    medicine_kit_btn.setIcon(R.drawable.medicine_kit_icon_clicked);
                    main_btn.setIcon(R.drawable.main_icon);
                    search_btn.setIcon(R.drawable.search_icon);
                    settings_btn.setIcon(R.drawable.settings);
                }

                // 메인 페이지로 이동
                else if(position == 2){
                    selected = main_fragment;
                    alarm_btn.setIcon(R.drawable.reminder);
                    medicine_kit_btn.setIcon(R.drawable.medicine_kit_icon);
                    main_btn.setIcon(R.drawable.main_icon_clicked);
                    search_btn.setIcon(R.drawable.search_icon);
                    settings_btn.setIcon(R.drawable.settings);
                }

                // 검색 페이지로 이동
                else if(position == 3){
                    selected = search_fragment;
                    alarm_btn.setIcon(R.drawable.reminder);
                    medicine_kit_btn.setIcon(R.drawable.medicine_kit_icon);
                    main_btn.setIcon(R.drawable.main_icon);
                    search_btn.setIcon(R.drawable.search_icon_clicked);
                    settings_btn.setIcon(R.drawable.settings);
                    }

                // 세팅 페이지로 이동
                else if(position == 4) {
                    selected = setting_fragment;
                    alarm_btn.setIcon(R.drawable.reminder);
                    medicine_kit_btn.setIcon(R.drawable.medicine_kit_icon);
                    main_btn.setIcon(R.drawable.main_icon);
                    search_btn.setIcon(R.drawable.search_icon);
                    settings_btn.setIcon(R.drawable.settings_icon_clicked);
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}