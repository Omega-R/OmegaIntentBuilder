package com.omega_r.libs.omegaintentbuilder.types

import android.content.Intent

enum class CalendarActionTypes(val intentAction: String) {

  VIEW_DATE(Intent.ACTION_VIEW),
  VIEW_EVENT(Intent.ACTION_VIEW),
  EDIT_EVENT(Intent.ACTION_EDIT),
  INSERT_EVENT(Intent.ACTION_INSERT)

}