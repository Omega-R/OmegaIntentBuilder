package com.omega_r.libs.omegaintentbuilder.builders

import android.app.SearchManager
import android.content.Context
import android.content.Intent

class SearchWebIntentBuilder : BaseActivityBuilder() {
    private var query: String? = null

    /**
     * Set the query to the web browser
     *
     * @param query String
     * @return This SearchWebIntentBuilder for method chaining
     */
    fun query(query: String): SearchWebIntentBuilder {
        this.query = query
        return this
    }

    override fun createIntent(context: Context): Intent {
        return Intent(Intent.ACTION_WEB_SEARCH).apply {
            query?.let {
                putExtra(SearchManager.QUERY, query)
            }
        }
    }

}