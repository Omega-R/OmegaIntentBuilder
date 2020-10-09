package com.omega_r.libs.omegaintentbuilder.builders

import android.app.SearchManager.QUERY
import android.content.Context
import android.content.Intent

class SearchWebIntentBuilder : BaseActivityBuilder() {
    private var query: String? = null

    /**
     * Set the query to the web browser
     * is the text to search for. If it is a url starts with http or https,
     * the site will be opened.
     * If it is plain text, Google search will be applied.
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
                putExtra(QUERY, query)
            }
        }
    }

}