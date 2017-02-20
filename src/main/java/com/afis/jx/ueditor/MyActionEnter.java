package com.afis.jx.ueditor;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.afis.jx.ueditor.define.ActionMap;
import com.afis.jx.ueditor.define.AppInfo;
import com.afis.jx.ueditor.define.BaseState;
import com.afis.jx.ueditor.define.State;
import com.afis.jx.ueditor.hunter.FileManager;
import com.afis.jx.ueditor.hunter.ImageHunter;
import com.afis.jx.ueditor.upload.Uploader;
/**
 * 基于百度ueditor修改。
 * @author Sunxiupeng on 2015年3月14日
 */
public class MyActionEnter {

    private HttpServletRequest request = null;

    private String actionType = null;

    private MyConfigManager configManager = null;

    public MyActionEnter(HttpServletRequest request, InputStream is,String location,String virtualPath) {

        this.request = request;
        this.actionType = request.getParameter("action");
        this.configManager = MyConfigManager.getInstance(is,location,virtualPath);

    }

    public String exec() throws JSONException {

        String callbackName = this.request.getParameter("callback");

        if (callbackName != null) {

            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
            }

            return callbackName + "(" + this.invoke() + ");";

        } else {
            return this.invoke();
        }

    }

    public String invoke() throws JSONException {

        if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        }

        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
        }

        State state = null;

        int actionCode = ActionMap.getType(this.actionType);

        Map<String, Object> conf = null;

        switch (actionCode) {

        case ActionMap.CONFIG:
            return this.configManager.getAllConfig().toString();

        case ActionMap.UPLOAD_IMAGE:
        case ActionMap.UPLOAD_SCRAWL:
        case ActionMap.UPLOAD_VIDEO:
        case ActionMap.UPLOAD_FILE:
            conf = this.configManager.getConfig(actionCode);
            state = new Uploader(request, conf).doExec();
            break;

        case ActionMap.CATCH_IMAGE:
            conf = configManager.getConfig(actionCode);
            String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
            state = new ImageHunter(conf).capture(list);
            break;

        case ActionMap.LIST_IMAGE:
        case ActionMap.LIST_FILE:
            conf = configManager.getConfig(actionCode);
            int start = this.getStartIndex();
            state = new FileManager(conf).listFile(start);
            break;

        }

        return state.toJSONString();

    }

    public int getStartIndex() {

        String start = this.request.getParameter("start");

        try {
            return Integer.parseInt(start);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * callback参数验证
     */
    public boolean validCallbackName(String name) {

        if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
            return true;
        }

        return false;

    }

}