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
import com.hepsiburada.dgrubuodev2.databinding.FragmentProfileBinding
import com.hepsiburada.dgrubuodev2.ui.adapter.FoodsAdapter

class HomeFragment : Fragment() {

    private var adapter: FoodsAdapter? = null
    private var database = FirebaseFirestore.getInstance()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val query = database.collection("foods")

        val options = FirestoreRecyclerOptions.Builder<Foods>()
            .setQuery(query, Foods::class.java)
            .build()

        adapter = FoodsAdapter(options)

        binding.apply {
            binding.recyclerview.setHasFixedSize(true)
            binding.recyclerview.layoutManager = LinearLayoutManager(context)
            binding.recyclerview.adapter = adapter

            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.floatingActionButton.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_homeFragment_to_addFoodFragment)
        }

        binding.ivProfileIcon.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
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