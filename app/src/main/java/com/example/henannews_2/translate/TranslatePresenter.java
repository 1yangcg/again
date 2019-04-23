package com.example.henannews_2.translate;


import com.example.henannews_2.base.BasePresenter;
import com.example.henannews_2.base.ModelManager;

/**
 * 调用Model的方法获取数据，并处理数据(业务逻辑)
 */
public class TranslatePresenter extends BasePresenter<TranslateView> {

    public void translate(String doctype, String type, String i) {
        TranslateModel model = ModelManager.getInstance().getModel(TranslateModel.class);
        model.translate(doctype, type, i, new TranslateCallback() {
            @Override
            public void onSuccess(WordBean data) {
                if (isAttach()) {
                    view.onSuccess(data);
                }
            }

            @Override
            public void onFailure(String message) {
                if (isAttach()) {
                    view.onFailure(message);
                }
            }
        });

    }
}
