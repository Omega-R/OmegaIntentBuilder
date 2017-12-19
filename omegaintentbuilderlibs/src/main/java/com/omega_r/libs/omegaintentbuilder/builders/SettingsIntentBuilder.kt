package com.omega_r.libs.omegaintentbuilder.builders

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi

class SettingsIntentBuilder(context: Context): BaseBuilder(context) {

  private var action: String = Settings.ACTION_SETTINGS

  fun wifi(): SettingsIntentBuilder {
    action = Settings.ACTION_WIFI_SETTINGS
    return this
  }

  fun locationSource(): SettingsIntentBuilder {
    action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
    return this
  }

  fun accessibility(): SettingsIntentBuilder {
    action = Settings.ACTION_ACCESSIBILITY_SETTINGS
    return this
  }

  fun addAccount(): SettingsIntentBuilder {
    action = Settings.ACTION_ADD_ACCOUNT
    return this
  }

  fun airplaneMode(): SettingsIntentBuilder {
    action = Settings.ACTION_AIRPLANE_MODE_SETTINGS
    return this
  }

  fun apn(): SettingsIntentBuilder {
    action = Settings.ACTION_APN_SETTINGS
    return this
  }

  fun applicationDetails(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    return this
  }

  fun development(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
    return this
  }

  fun application(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
  fun batterySaver(): SettingsIntentBuilder {
    action = Settings.ACTION_BATTERY_SAVER_SETTINGS
    return this
  }

  fun bluetooth(): SettingsIntentBuilder {
    action = Settings.ACTION_BLUETOOTH_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun captioning(): SettingsIntentBuilder {
    action = Settings.ACTION_CAPTIONING_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun cast(): SettingsIntentBuilder {
    action = Settings.ACTION_CAST_SETTINGS
    return this
  }

  fun dataRoaming(): SettingsIntentBuilder {
    action = Settings.ACTION_DATA_ROAMING_SETTINGS
    return this
  }

  fun data(): SettingsIntentBuilder {
    action = Settings.ACTION_DATE_SETTINGS
    return this
  }

  fun deviceInfo(): SettingsIntentBuilder {
    action = Settings.ACTION_DEVICE_INFO_SETTINGS
    return this
  }

  fun display(): SettingsIntentBuilder {
    action = Settings.ACTION_DISPLAY_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
  fun dream(): SettingsIntentBuilder {
    action = Settings.ACTION_DREAM_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun keyboard(): SettingsIntentBuilder {
    action = Settings.ACTION_HARD_KEYBOARD_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun home(): SettingsIntentBuilder {
    action = Settings.ACTION_HOME_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun ignoreBackgroundDataRestrictions(): SettingsIntentBuilder {
    action = Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun ignoreBatteryOptimization(): SettingsIntentBuilder {
    action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
    return this
  }

  fun inputMethod(): SettingsIntentBuilder {
    action = Settings.ACTION_INPUT_METHOD_SETTINGS
    return this
  }

  fun inputMethodSubtype(): SettingsIntentBuilder {
    action = Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS
    return this
  }

  fun internalStorage(): SettingsIntentBuilder {
    action = Settings.ACTION_INTERNAL_STORAGE_SETTINGS
    return this
  }

  fun locale(): SettingsIntentBuilder {
    action = Settings.ACTION_LOCALE_SETTINGS
    return this
  }

  fun manageAllApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS
    return this
  }

  fun manageApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun manageDefaultApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun manageOverlay(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun manageWrite(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_WRITE_SETTINGS
    return this
  }

  fun memoryCard(): SettingsIntentBuilder {
    action = Settings.ACTION_MEMORY_CARD_SETTINGS
    return this
  }

  fun networkOperator(): SettingsIntentBuilder {
    action = Settings.ACTION_NETWORK_OPERATOR_SETTINGS
    return this
  }

  fun nfsSharing(): SettingsIntentBuilder {
    action = Settings.ACTION_NFCSHARING_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun nfcPayment(): SettingsIntentBuilder {
    action = Settings.ACTION_NFC_PAYMENT_SETTINGS
    return this
  }

  fun nfc(): SettingsIntentBuilder {
    action = Settings.ACTION_NFC_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
  fun notificationListener(): SettingsIntentBuilder {
    action = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun notificationPolicyAccess(): SettingsIntentBuilder {
    action = Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun print(): SettingsIntentBuilder {
    action = Settings.ACTION_PRINT_SETTINGS
    return this
  }

  fun privacy(): SettingsIntentBuilder {
    action = Settings.ACTION_PRIVACY_SETTINGS
    return this
  }

  fun quickLaunch(): SettingsIntentBuilder {
    action = Settings.ACTION_QUICK_LAUNCH_SETTINGS
    return this
  }

  @SuppressLint("BatteryLife")
  @RequiresApi(Build.VERSION_CODES.M)
  fun requestIgnoreBatteryOptimizations(): SettingsIntentBuilder {
    action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    return this
  }

  fun search(): SettingsIntentBuilder {
    action = Settings.ACTION_SEARCH_SETTINGS
    return this
  }

  fun security(): SettingsIntentBuilder {
    action = Settings.ACTION_SECURITY_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun regulatoryInfo(): SettingsIntentBuilder {
    action = Settings.ACTION_SHOW_REGULATORY_INFO
    return this
  }

  fun sound(): SettingsIntentBuilder {
    action = Settings.ACTION_SOUND_SETTINGS
    return this
  }

  fun sync(): SettingsIntentBuilder {
    action = Settings.ACTION_SYNC_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun usageAccess(): SettingsIntentBuilder {
    action = Settings.ACTION_USAGE_ACCESS_SETTINGS
    return this
  }

  fun userDictionary(): SettingsIntentBuilder {
    action = Settings.ACTION_USER_DICTIONARY_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlAirplaneMode(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_AIRPLANE_MODE
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlBatterySaverMode(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlDontDisturb(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE
    return this
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun voiceInput(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_INPUT_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun vpn(): SettingsIntentBuilder {
    action = Settings.ACTION_VPN_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun vrListener(): SettingsIntentBuilder {
    action = Settings.ACTION_VR_LISTENER_SETTINGS
    return this
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun webview(): SettingsIntentBuilder {
    action = Settings.ACTION_WEBVIEW_SETTINGS
    return this
  }

  fun wifiIp(): SettingsIntentBuilder {
    action = Settings.ACTION_WIFI_IP_SETTINGS
    return this
  }

  fun wireless(): SettingsIntentBuilder {
    action = Settings.ACTION_WIRELESS_SETTINGS
    return this
  }

  fun authority(): SettingsIntentBuilder {
    action = Settings.AUTHORITY
    return this
  }

  @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
  fun accountTypes(): SettingsIntentBuilder {
    action = Settings.EXTRA_ACCOUNT_TYPES
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun airplaneModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_AIRPLANE_MODE_ENABLED
    return this
  }

  fun extraAuthorities(): SettingsIntentBuilder {
    action = Settings.EXTRA_AUTHORITIES
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun batterySaverModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_BATTERY_SAVER_MODE_ENABLED
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun extraDontDisturbModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_DO_NOT_DISTURB_MODE_ENABLED
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun extraDontDisturbModeMinutes(): SettingsIntentBuilder {
    action = Settings.EXTRA_DO_NOT_DISTURB_MODE_MINUTES
    return this
  }

  fun extraInputMethodId(): SettingsIntentBuilder {
    action = Settings.EXTRA_INPUT_METHOD_ID
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun categoryUsageAccessConfig(): SettingsIntentBuilder {
    action = Settings.INTENT_CATEGORY_USAGE_ACCESS_CONFIG
    return this
  }

  @RequiresApi(Build.VERSION_CODES.M)
  fun metadataUsageAccessReason(): SettingsIntentBuilder {
    action = Settings.METADATA_USAGE_ACCESS_REASON
    return this
  }
  
  override fun createIntent(): Intent {
    return Intent(action)
  }

}