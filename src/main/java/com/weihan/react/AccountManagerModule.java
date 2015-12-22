package com.weihan.react;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.accounts.Account;
import android.accounts.AccountManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Promise;

public class AccountManagerModule extends ReactContextBaseJavaModule {
  Activity mActivity = null;
  AccountManager mAccountManager = null;


  public AccountManagerModule(ReactApplicationContext reactApplicationContext, Activity activity) {
    super(reactApplicationContext);
    mAccountManager = AccountManager.get(reactApplicationContext.getApplicationContext());
    mActivity = activity;
  }

  @Override
  public String getName() {
    return "AccountManagerAndroid";
  }

  @ReactMethod
  public void getAccounts(Promise promise){
    Account[] accounts = mAccountManager.getAccounts();
    String[] strAccounts = new String[accounts.length];
    Gson gson = new Gson();
    for(int i=0; i< accounts.length; i++){
        strAccounts[i] = gson.toJson(accounts[i]);
    }
    promise.resolve("");
  }
}
