package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hepsiburada.dgrubuodev2.R
import com.hepsiburada.dgrubuodev2.data.model.Foods
import com.hepsiburada.dgrubuodev2.databinding.FragmentHomeBinding
import com.hepsiburada.dgrubuodev2.ui.adapter.FoodsAdapter
import com.hepsiburada.dgrubuodev2.ui.adapter.HomeFragmentAdapter


class HomeFragment : Fragment() {

    private var adapter: FoodsAdapter? = null
    private var database = FirebaseFirestore.getInstance()
    private var reference:CollectionReference = database.collection("foods")
    private var firebaseIdList: ArrayList<String> = ArrayList()
    private val names: ArrayList<Foods> = ArrayList()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var q:Query? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        binding.apply {
            binding.recyclerview.setHasFixedSize(true)
            binding.recyclerview.layoutManager = LinearLayoutManager(context)
            binding.recyclerview.adapter = adapter

            q = database.collection("foods")
            showAdapter(q!!)

            return binding.root
        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.toString().length == 0) {
                    q = database.collection("foods")
                    Log.d("7777777","ENTRANCE")
                    showAdapter(q!!)
                } // This is used as if user erases the characters in the search field.
                else {
                    q = reference.orderBy("foodName")
                        .startAt(charSequence.toString().trim { it <= ' ' }).endAt(
                            charSequence.toString()
                                .trim { it <= ' ' } + "\uf8ff") // name - the field for which you want to make search

                    showAdapter(q!!)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun showAdapter(q1:Query){
        q1.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->

            if (task.isSuccessful) {

                firebaseIdList.clear()
                names.clear()

                for (document in task.result) {
                    firebaseIdList.add(document.id)
                    val model: Foods = document.toObject(Foods::class.java)
                    names.add(model)
                }

                binding.recyclerview.adapter = HomeFragmentAdapter(names,firebaseIdList)
            }
        })
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