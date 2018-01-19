/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * SettingsIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 19, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi

/**
 * SettingsIntentBuilder is a helper for constructing settings intent
 */
class SettingsIntentBuilder(context: Context): BaseBuilder(context) {

  private var action: String = Settings.ACTION_SETTINGS

  /**
   * Activity Action: Show settings to allow configuration of Wi-Fi.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun wifi(): SettingsIntentBuilder {
    action = Settings.ACTION_WIFI_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of current location sources.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun locationSource(): SettingsIntentBuilder {
    action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for accessibility modules.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun accessibility(): SettingsIntentBuilder {
    action = Settings.ACTION_ACCESSIBILITY_SETTINGS
    return this
  }

  /**
   * Activity Action: Show add account screen for creating a new account.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun addAccount(): SettingsIntentBuilder {
    action = Settings.ACTION_ADD_ACCOUNT
    return this
  }

  /**
   * Activity Action: Show settings to allow entering/exiting airplane mode.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun airplaneMode(): SettingsIntentBuilder {
    action = Settings.ACTION_AIRPLANE_MODE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of APNs.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun apn(): SettingsIntentBuilder {
    action = Settings.ACTION_APN_SETTINGS
    return this
  }

  /**
   * Activity Action: Show screen of details about a particular application.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun applicationDetails(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of application development-related settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun development(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of application-related settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun application(): SettingsIntentBuilder {
    action = Settings.ACTION_APPLICATION_SETTINGS
    return this
  }

  /**
   * Activity Action: Show battery saver settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
  fun batterySaver(): SettingsIntentBuilder {
    action = Settings.ACTION_BATTERY_SAVER_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of Bluetooth.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun bluetooth(): SettingsIntentBuilder {
    action = Settings.ACTION_BLUETOOTH_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for video captioning.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun captioning(): SettingsIntentBuilder {
    action = Settings.ACTION_CAPTIONING_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of cast endpoints.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun cast(): SettingsIntentBuilder {
    action = Settings.ACTION_CAST_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for selection of 2G/3G.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun dataRoaming(): SettingsIntentBuilder {
    action = Settings.ACTION_DATA_ROAMING_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of date and time.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun data(): SettingsIntentBuilder {
    action = Settings.ACTION_DATE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show general device information settings (serial number, software version, phone number, etc.).
   * @return This SettingsIntentBuilder for method chaining
   */
  fun deviceInfo(): SettingsIntentBuilder {
    action = Settings.ACTION_DEVICE_INFO_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of display.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun display(): SettingsIntentBuilder {
    action = Settings.ACTION_DISPLAY_SETTINGS
    return this
  }

  /**
   * Activity Action: Show Daydream settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
  fun dream(): SettingsIntentBuilder {
    action = Settings.ACTION_DREAM_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to configure the hardware keyboard.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun keyboard(): SettingsIntentBuilder {
    action = Settings.ACTION_HARD_KEYBOARD_SETTINGS
    return this
  }

  /**
   * Activity Action: Show Home selection settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun home(): SettingsIntentBuilder {
    action = Settings.ACTION_HOME_SETTINGS
    return this
  }

  /**
   * Activity Action: Show screen for controlling background data restrictions for a particular application.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun ignoreBackgroundDataRestrictions(): SettingsIntentBuilder {
    action = Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show screen for controlling which apps can ignore battery optimizations.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun ignoreBatteryOptimization(): SettingsIntentBuilder {
    action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to configure input methods, in particular allowing the user to enable input methods.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun inputMethod(): SettingsIntentBuilder {
    action = Settings.ACTION_INPUT_METHOD_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to enable/disable input method subtypes.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun inputMethodSubtype(): SettingsIntentBuilder {
    action = Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for internal storage.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun internalStorage(): SettingsIntentBuilder {
    action = Settings.ACTION_INTERNAL_STORAGE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of locale.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun locale(): SettingsIntentBuilder {
    action = Settings.ACTION_LOCALE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to manage all applications.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun manageAllApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to manage installed applications.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun manageApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show Default apps settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun manageDefaultApps(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show screen for controlling which apps can draw on top of other apps.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun manageOverlay(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
    return this
  }

  /**
   * Activity Action: Show screen for controlling which apps are allowed to write/modify system settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun manageWrite(): SettingsIntentBuilder {
    action = Settings.ACTION_MANAGE_WRITE_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for memory card storage.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun memoryCard(): SettingsIntentBuilder {
    action = Settings.ACTION_MEMORY_CARD_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings for selecting the network operator.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun networkOperator(): SettingsIntentBuilder {
    action = Settings.ACTION_NETWORK_OPERATOR_SETTINGS
    return this
  }

  /**
   * Activity Action: Show NFC Sharing settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun nfsSharing(): SettingsIntentBuilder {
    action = Settings.ACTION_NFCSHARING_SETTINGS
    return this
  }

  /**
   * Activity Action: Show NFC Tap & Pay settings
   * This shows UI that allows the user to configure Tap&Pay settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun nfcPayment(): SettingsIntentBuilder {
    action = Settings.ACTION_NFC_PAYMENT_SETTINGS
    return this
  }

  /**
   * Activity Action: Show NFC settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun nfc(): SettingsIntentBuilder {
    action = Settings.ACTION_NFC_SETTINGS
    return this
  }

  /**
   * Activity Action: Show Notification listener settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
  fun notificationListener(): SettingsIntentBuilder {
    action = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
    return this
  }

  /**
   * Activity Action: Show Do Not Disturb access settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun notificationPolicyAccess(): SettingsIntentBuilder {
    action = Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show the top level print settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.KITKAT)
  fun print(): SettingsIntentBuilder {
    action = Settings.ACTION_PRINT_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of privacy options.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun privacy(): SettingsIntentBuilder {
    action = Settings.ACTION_PRIVACY_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of quick launch shortcuts.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun quickLaunch(): SettingsIntentBuilder {
    action = Settings.ACTION_QUICK_LAUNCH_SETTINGS
    return this
  }

  /**
   * Activity Action: Ask the user to allow an app to ignore battery optimizations
   * (that is, put them on the whitelist of apps shown by ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).
   * @return This SettingsIntentBuilder for method chaining
   */
  @SuppressLint("BatteryLife")
  @RequiresApi(Build.VERSION_CODES.M)
  fun requestIgnoreBatteryOptimizations(): SettingsIntentBuilder {
    action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    return this
  }

  /**
   * Activity Action: Show settings for global search
   * @return This SettingsIntentBuilder for method chaining
   */
  fun search(): SettingsIntentBuilder {
    action = Settings.ACTION_SEARCH_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of security and location privacy.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun security(): SettingsIntentBuilder {
    action = Settings.ACTION_SECURITY_SETTINGS
    return this
  }

  /**
   * Activity Action: Show the regulatory information screen for the device.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun regulatoryInfo(): SettingsIntentBuilder {
    action = Settings.ACTION_SHOW_REGULATORY_INFO
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of sound and volume.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun sound(): SettingsIntentBuilder {
    action = Settings.ACTION_SOUND_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of sync settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun sync(): SettingsIntentBuilder {
    action = Settings.ACTION_SYNC_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to control access to usage information.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun usageAccess(): SettingsIntentBuilder {
    action = Settings.ACTION_USAGE_ACCESS_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to manage the user input dictionary.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun userDictionary(): SettingsIntentBuilder {
    action = Settings.ACTION_USER_DICTIONARY_SETTINGS
    return this
  }

  /**
   * Activity Action: Modify Airplane mode settings using a voice command.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlAirplaneMode(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_AIRPLANE_MODE
    return this
  }

  /**
   * Activity Action: Modify Battery Saver mode setting using a voice command.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlBatterySaverMode(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE
    return this
  }

  /**
   * Activity Action: Modify do not disturb mode settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun voiceControlDontDisturb(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE
    return this
  }

  /**
   * Activity Action: Show settings to configure input methods, in particular allowing the user to enable input methods.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun voiceInput(): SettingsIntentBuilder {
    action = Settings.ACTION_VOICE_INPUT_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of VPN.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun vpn(): SettingsIntentBuilder {
    action = Settings.ACTION_VPN_SETTINGS
    return this
  }

  /**
   * Activity Action: Show VR listener settings.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun vrListener(): SettingsIntentBuilder {
    action = Settings.ACTION_VR_LISTENER_SETTINGS
    return this
  }

  /**
   * Activity Action: Allows user to select current webview implementation.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.N)
  fun webview(): SettingsIntentBuilder {
    action = Settings.ACTION_WEBVIEW_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of a static IP address for Wi-Fi.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun wifiIp(): SettingsIntentBuilder {
    action = Settings.ACTION_WIFI_IP_SETTINGS
    return this
  }

  /**
   * Activity Action: Show settings to allow configuration of wireless controls such as Wi-Fi, Bluetooth and Mobile networks.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun wireless(): SettingsIntentBuilder {
    action = Settings.ACTION_WIRELESS_SETTINGS
    return this
  }

  /**
   * @return This SettingsIntentBuilder for method chaining
   */
  fun authority(): SettingsIntentBuilder {
    action = Settings.AUTHORITY
    return this
  }

  /**
   * Activity Extra: Limit available options in launched activity based on the given account types.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
  fun accountTypes(): SettingsIntentBuilder {
    action = Settings.EXTRA_ACCOUNT_TYPES
    return this
  }

  /**
   * Activity Extra: Enable or disable Airplane Mode.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun airplaneModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_AIRPLANE_MODE_ENABLED
    return this
  }

  /**
   * Activity Extra: Limit available options in launched activity based on the given authority.
   * @return This SettingsIntentBuilder for method chaining
   */
  fun extraAuthorities(): SettingsIntentBuilder {
    action = Settings.EXTRA_AUTHORITIES
    return this
  }

  /**
   * Activity Extra: Enable or disable Battery saver mode.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun batterySaverModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_BATTERY_SAVER_MODE_ENABLED
    return this
  }

  /**
   * Activity Extra: Enable or disable Do Not Disturb mode.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun extraDontDisturbModeEnabled(): SettingsIntentBuilder {
    action = Settings.EXTRA_DO_NOT_DISTURB_MODE_ENABLED
    return this
  }

  /**
   * Activity Extra: How many minutes to enable do not disturb mode for.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun extraDontDisturbModeMinutes(): SettingsIntentBuilder {
    action = Settings.EXTRA_DO_NOT_DISTURB_MODE_MINUTES
    return this
  }

  /**
   * @return This SettingsIntentBuilder for method chaining
   */
  fun extraInputMethodId(): SettingsIntentBuilder {
    action = Settings.EXTRA_INPUT_METHOD_ID
    return this
  }

  /**
   * Activity Category: Show application settings related to usage access.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun categoryUsageAccessConfig(): SettingsIntentBuilder {
    action = Settings.INTENT_CATEGORY_USAGE_ACCESS_CONFIG
    return this
  }

  /**
   * Metadata key: Reason for needing usage access.
   * @return This SettingsIntentBuilder for method chaining
   */
  @RequiresApi(Build.VERSION_CODES.M)
  fun metadataUsageAccessReason(): SettingsIntentBuilder {
    action = Settings.METADATA_USAGE_ACCESS_REASON
    return this
  }

  override fun createIntent(): Intent {
    return Intent(action)
  }

}