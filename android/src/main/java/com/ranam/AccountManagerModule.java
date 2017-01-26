package com.ranam;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.util.Patterns;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountManagerModule extends ReactContextBaseJavaModule {
  AccountManager mAccountManager = null;


  public AccountManagerModule(ReactApplicationContext reactApplicationContext) {
    super(reactApplicationContext);
    mAccountManager = AccountManager.get(reactApplicationContext.getApplicationContext());
  }

  @Override
  public String getName() {
    return "AccountManagerAndroid";
  }

  @ReactMethod
  public void getEmail(Promise promise) {
    Pattern emailPattern = Patterns.EMAIL_ADDRESS;
    Account[] accounts = mAccountManager.getAccounts();
    for (Account account : accounts) {
      if (emailPattern.matcher(account.name).matches()) {
        promise.resolve(account.name);
      }
    }
    promise.reject(null, "no email associated");
  }
}
