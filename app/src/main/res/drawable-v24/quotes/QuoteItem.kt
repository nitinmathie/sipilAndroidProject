package net.simplifiedcoding.mvvmsampleapp.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import com.example.hopelastrestart1.R
import com.example.hopelastrestart1.databinding.ItemQuoteBinding
import com.example.hopelastrestart1.data.db.entities.Quote


class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}