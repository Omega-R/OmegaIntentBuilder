package com.omega_r.libs.omegaintentbuilder.utils

class ExtensionUtils {

  companion object {
    fun Int?.isNullOrLessZero(): Boolean {
      if (this == null || this < 0) return true
      return false
    }
  }

}