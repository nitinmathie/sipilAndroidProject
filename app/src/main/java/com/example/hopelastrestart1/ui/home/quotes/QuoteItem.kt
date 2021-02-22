package com.example.hopelastrestart1.ui.home.quotes

import com.example.hopelastrestart1.R
import com.xwray.groupie.databinding.BindableItem

import com.example.hopelastrestart1.data.db.entities.Quote
import com.example.hopelastrestart1.databinding.ItemQuoteBinding


class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}