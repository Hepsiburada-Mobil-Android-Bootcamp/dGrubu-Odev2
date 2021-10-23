package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.FragmentHomeBinding
import com.hepsiburada.dgrubuodev2.ui.adapter.FoodsAdapter

class HomeFragment : Fragment() {

    private  var adapter: FoodsAdapter? = null
    private var database = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        val query = database.collection("foods")

        val options = FirestoreRecyclerOptions.Builder<Foods>()
            .setQuery(query, Foods::class.java)
            .build()

        adapter = FoodsAdapter(options)

        binding.apply {
            binding.recyclerview.setHasFixedSize(true)
            binding.recyclerview.layoutManager = LinearLayoutManager(context)
            binding.recyclerview.adapter = adapter


        binding.floatingActionButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_homeFragment_to_addFoodFragment)
        }
        return binding.root
    }

    }
    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}