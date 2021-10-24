package com.hepsiburada.dgrubuodev2.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


class HomeFragment : Fragment() {

    private var adapter: FoodsAdapter? = null
    private var database = FirebaseFirestore.getInstance()
    //private var reference:CollectionReference = database.collection("foods")

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //var q:Query? = null

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

        /*binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.toString().length == 0) {
                    q = database.collection("foods")
                    showAdapter(q!!)
                } // This is used as if user erases the characters in the search field.
                else {
                    q = reference.orderBy("name")
                        .startAt(charSequence.toString().trim { it <= ' ' }).endAt(
                            charSequence.toString()
                                .trim { it <= ' ' } + "\uf8ff") // name - the field for which you want to make search
                    showAdapter(q!!)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun afterTextChanged(editable: Editable) {}
        })*/
    }

    /*fun showAdapter(q1:Query){
        q1.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot?> { task ->
            val names: MutableList<Foods> = ArrayList()
            if (task.isSuccessful) {
                for (document in task.result) {
                    val model: Foods = document.toObject(Foods::class.java)
                    names.add(model)
                }
                val options1 = FirestoreRecyclerOptions.Builder<Foods>()
                    .setQuery(q1, Foods::class.java)
                    .build()
                adapter = FoodsAdapter(options1)

            }
        })
    }*/

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