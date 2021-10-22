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
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.FragmentDetailsBinding
import com.hepsiburada.dgrubuodev2.viewmodel.DetailsFragmentViewModel
import com.hepsiburada.dgrubuodev2.viewmodel.EditFragmentViewModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    val detailsViewModel: DetailsFragmentViewModel by viewModels()
    var uuid:String?=null
    lateinit var currentFood: Foods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_details, container, false)
        binding.detailsFragment=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
           uuid=DetailsFragmentArgs.fromBundle(it).uuid
           currentFood= detailsViewModel.getRecipe(uuid)!!
            setViews(currentFood)
        }

    }


    private fun setViews(currentFood: Foods){
        binding.apply {
            foodNameTextView.text=currentFood.foodName
            kcalValueTextView.text=currentFood.foodCalory.toString()
            ingredientsDetailsTextView.text=currentFood.foodIngredients
            directionsDetailsTextView.text=currentFood.foodRecipe
        }
        Picasso.get().load(currentFood.foodImg).into(binding.foodImageView)
    }



    fun deleteOnClick(view: View){
        buildAlertDialog(uuid).show()
    }

    fun editOnClick(){
        val action=DetailsFragmentDirections.actionDetailsFragmentToEditFragment(uuid)
        Navigation.findNavController(requireView()).navigate(action)
    }



    fun buildAlertDialog(uuid:String?):AlertDialog{
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