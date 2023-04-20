package com.example.tpconverter.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpconverter.adapter.RateAdapter
import com.example.tpconverter.configuration.AppConfig
import com.example.tpconverter.databinding.FragmentHomeBinding
import com.example.tpconverter.httputil.HttpUtil
import com.example.tpconverter.json.CurrencyConverter
import com.example.tpconverter.json.RateConverter
import com.example.tpconverter.json.MergeConverter
import com.example.tpconverter.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*        val ratesString = getRates()
        val currenciesString = getCurrencies()

        val convertRates = RateConverter.convertRates(ratesString)
        val convertCurrencies = CurrencyConverter.convertCurrencies(currenciesString)*/

        displayRates()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun displayRates() = runBlocking {

        val rateList: RecyclerView = binding.rateRecyclerView

        val db = AppDatabase.getInstance(requireContext())

        // Load rates from database and display them
        val ratesFromDb = db.rateDao().getRates()

        if (ratesFromDb.isEmpty()) {
            // Fetch rates and currencies from API
            val rates = withContext(Dispatchers.IO) {
                HttpUtil.readUrl(AppConfig.API_RATES)
            }
            val currencies = withContext(Dispatchers.IO) {
                HttpUtil.readUrl(AppConfig.API_CURRENCIES)
            }

            // Convert rates and currencies
            val convertRates = RateConverter.convertRates(rates)
            val convertCurrencies = CurrencyConverter.convertCurrencies(currencies)

            withContext(Dispatchers.IO) {
                var ratesToDb = MergeConverter.merge(convertRates, convertCurrencies)
                Log.d(" rates : ", ratesToDb.toString())
                // Merge rates and currencies and insert into database
                db.rateDao().insertAll(ratesToDb)
            }

            // Load rates from database again
            val ratesFromDbAgain = db.rateDao().getRates()
            rateList.adapter = RateAdapter(requireContext(), ratesFromDbAgain)
        } else {
            // Display rates from database
            rateList.adapter = RateAdapter(requireContext(), ratesFromDb)
        }

        rateList.layoutManager = LinearLayoutManager(requireContext())
    }

}
/*
private fun getRates(): String {
    return IoUtil.readTextFile(
        requireContext(),
        "data/rates.json"
    )
}

private fun getCurrencies(): String {
    return IoUtil.readTextFile(
        requireContext(),
        "data/currencies.json"
    )
}*/
