package com.retail.jewels.fauxiq.accounting.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.retail.jewels.fauxiq.accounting.R
import com.retail.jewels.fauxiq.accounting.databinding.HomeScreenBinding
import com.retail.jewels.fauxiq.accounting.db.room.converters.PeopleType
import com.retail.jewels.fauxiq.accounting.utils.toast

class HomeScreen : Fragment() {
    private lateinit var binding: HomeScreenBinding
    private lateinit var navController: NavController
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_screen, container, false)
        binding.peopleClickListener = peopleClickListener
        binding.itemListingClickListener = itemListingClickListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }


    private val peopleClickListener = View.OnClickListener {
        navController.navigate(R.id.homeFragment)
        val action = HomeScreenDirections.homeFragment()
        action.peopleKind = PeopleType.PARTY.id
        NavHostFragment.findNavController(this).navigate(action)

    }

    private val itemListingClickListener = View.OnClickListener {
        toast(requireContext(), "Click")
//        listener?.itemSelection()
        navController.navigate(R.id.itemListViewPagerFragment)
    }


     override fun onAttach(context: Context) {
         super.onAttach(context)
         if (context is OnFragmentInteractionListener) {
             listener = context
         } else {
             throw RuntimeException("$context must implement OnFragmentInteractionListener")
         }
     }

     override fun onDetach() {
         super.onDetach()
         listener = null
     }

     interface OnFragmentInteractionListener {
         fun itemSelection()
     }

    companion object {

        const val CLASS_SIMPLE_NAME = "Home"
        const val PICK_DATABASE_FILE_REQUEST_CODE = 1234

        @JvmStatic
        fun newInstance() = HomeScreen()
    }

}