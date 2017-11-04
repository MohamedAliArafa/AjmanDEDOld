package com.zeowls.ajmanded.screens.Search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.zeowls.ajmanded.R;
import com.zeowls.ajmanded.adapters.ServiceAdapter;
import com.zeowls.ajmanded.models.ServiceModel;
import com.zeowls.ajmanded.utility.Localization;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.rv_service)
    RecyclerView mRecycleService;

    @BindView(R.id.et_toolbar_search)
    EditText ToolbarSearchEditText;

    ServiceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        List<ServiceModel> models = Realm.getDefaultInstance().where(ServiceModel.class).findAll();

        mAdapter = new ServiceAdapter(this, models, R.layout.list_item_service_search);
        mRecycleService.setAdapter(mAdapter);

        mRecycleService.setLayoutManager(new LinearLayoutManager(this));
        mRecycleService.setItemAnimator(new DefaultItemAnimator());
        mRecycleService.setNestedScrollingEnabled(false);
        mRecycleService.setHasFixedSize(true);

        ToolbarSearchEditText.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(ToolbarSearchEditText.getText().toString());
                handled = true;
            }
            return handled;
        });
    }

    private void search(String keyword) {
        List<ServiceModel> models;
        if (Localization.getCurrentLanguageID(this) == Localization.ARABIC_VALUE)
            models = Realm.getDefaultInstance()
                    .where(ServiceModel.class)
                    .contains("nameAr", keyword)
                    .findAll();
        else
            models = Realm.getDefaultInstance()
                    .where(ServiceModel.class)
                    .contains("name", keyword)
                    .findAll();
        mAdapter.updateData(models);
    }
}
