package com.example.tpconverter.ui.home

import android.os.Bundle
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
import com.example.tpconverter.io.IoUtil
import com.example.tpconverter.json.CurrencyConverter
import com.example.tpconverter.json.RateConverter
import com.example.tpconverter.model.MergeApi
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
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rateList: RecyclerView = binding.rateRecyclerView
/*        val ratesString = getRates()
        val currenciesString = getCurrencies()

        val convertRates = RateConverter.convertRates(ratesString)
        val convertCurrencies = CurrencyConverter.convertCurrencies(currenciesString)*/

        displayRates()


        rateList.adapter = RateAdapter(requireContext(), MergeApi.merge(convertRates, convertCurrencies))
        rateList.layoutManager=LinearLayoutManager(requireContext())


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun displayRates() = runBlocking {
        val rates = withContext(Dispatchers.IO) {
            HttpUtil.readUrl(AppConfig.API_RATES)
        }
        val currencies = withContext(Dispatchers.IO) {
            HttpUtil.readUrl(AppConfig.API_CURRENCIES)
        }
        val convertRates = RateConverter.convertRates(rates)
        val convertCurrencies = CurrencyConverter.convertCurrencies(currencies)

    }
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
    }
}