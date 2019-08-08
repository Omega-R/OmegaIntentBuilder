package com.omega_r.libs.omegaintentbuilder

import android.content.Context
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentBuilder
import com.omegar.libs.omegalaunchers.Launcher

/**
 * Created by Anton Knyazev on 2019-08-08.
 */
class IntentBuilderLauncher(private val intentBuilder: IntentBuilder) : Launcher {

    override fun launch(context: Context) {
        context.startActivity(intentBuilder.createIntent(context))
    }

}