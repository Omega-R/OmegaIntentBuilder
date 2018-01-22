package com.omega_r.libs.omegaintentbuilder.types

import android.provider.CalendarContract.Events

enum class CalendarAvailabilityTypes(val availability: Int) {

  /**
   * Indicates that this event takes up time and will conflict with other
   * events.
   */
  AVAILABILITY_BUSY(Events.AVAILABILITY_BUSY),
  /**
   * Indicates that this event is free time and will not conflict with
   * other events.
   */
  AVAILABILITY_FREE(Events.AVAILABILITY_FREE),
  /**
   * Indicates that the owner's availability may change, but should be
   * considered busy time that will conflict.
   */
  AVAILABILITY_TENTATIVE(Events.AVAILABILITY_TENTATIVE)

}