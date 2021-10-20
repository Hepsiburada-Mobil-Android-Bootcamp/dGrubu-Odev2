package com.hepsiburada.dgrubuodev2.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.databinding.FragmentDetailsBinding
import com.hepsiburada.dgrubuodev2.viewmodel.DetailsFragmentViewModel
import com.hepsiburada.dgrubuodev2.viewmodel.EditFragmentViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    val detailsViewModel: DetailsFragmentViewModel by viewModels()
    val uuid:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_details, container, false)
        binding.detailsFragment=this
        return binding.root
    }

    fun deleteOnClick(view: View){
        buildAlertDialog(uuid).show()
    }

    fun editOnClick(){


    }



    fun buildAlertDialog(uuid:Int?):AlertDialog{
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Continue", { dialog, id ->
                        detailsViewModel.deleteRecipe(uuid)
                    })
                setNegativeButton("Cancel", { dialog, id ->
                        // User cancelled the dialog
                    })
                setTitle("Delete Recipe")
                setMessage("Are you sure you want to delete this?")
            }
            builder.create()
        }

        return alertDialog!!
    }




}